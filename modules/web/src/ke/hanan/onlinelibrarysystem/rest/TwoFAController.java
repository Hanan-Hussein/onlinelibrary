package ke.hanan.onlinelibrarysystem.rest;

import com.haulmont.addon.restapi.api.auth.OAuthTokenIssuer;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.TrustedClientService;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.entity.Otp;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.rest.Model.LoginModel;
import ke.hanan.onlinelibrarysystem.rest.Model.TwoFAVerificationRequest;
import ke.hanan.onlinelibrarysystem.rest.Model.TwoFactorResponse;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("v2/oauth")
public class TwoFAController {
    @Inject
    private TrustedClientService trustedClientService;
    @Inject
    private DataManager dataManager;
    @Inject
    private Configuration configuration;
    @Inject
    private EmailService emailService;
    @Inject
    private Logger logger;
    @Inject
    private OAuthTokenIssuer oAuthTokenIssuer;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private MessageTools messageTools;
    @Inject
    private Metadata metadata;

    @PostMapping("authenticate")
        ResponseEntity login(@RequestBody LoginModel request){
        ResponseWrapper response = new ResponseWrapper();

        WebAuthConfig webAuthConfig =configuration.getConfig(WebAuthConfig.class);
        UserSession userSession;

        try{
            userSession = trustedClientService.getSystemSession(webAuthConfig.getTrustedClientPassword());
        }catch (LoginException e){
            logger.error("Error",e);
            throw new RuntimeException("Error during System Auth");
        }

        AppContext.setSecurityContext(new SecurityContext(userSession));

        try{
            Students students= dataManager.load(Students.class)
                    .query("select e from onlinelibrarysystem_Students e where e.login=:username and e.active=true")
                    .parameter("username",request.getUsername())
                    .one();

            logger.info("students"+students);

            if(!passwordEncryption.checkPassword(students,request.getPassword())){
                response.setCode(400);
                response.setMessage("Wrong Password");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            else if (passwordEncryption.checkPassword(students,request.getPassword())){
                OAuthTokenIssuer.OAuth2AccessTokenResult tokenRequest = oAuthTokenIssuer
                        .issueToken(students.getLogin(),messageTools.getDefaultLocale(), Collections.emptyMap());
                OAuth2AccessToken accessToken = tokenRequest.getAccessToken();

                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.CACHE_CONTROL,"no-store");
                headers.set(HttpHeaders.PRAGMA,"no-cache");
                return new ResponseEntity<>(accessToken,headers,HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error("Error",e);
        }
        response.setCode(404);
        response.setMessage("No user found with the provided username!");
        return new ResponseEntity(response,HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("send-token")
        ResponseEntity sendToken(@RequestBody LoginModel request) {
        ResponseWrapper response = new ResponseWrapper();

        WebAuthConfig webAuthConfig = configuration.getConfig(WebAuthConfig.class);
        UserSession systemSession;
        try {
            systemSession = trustedClientService.getSystemSession(webAuthConfig.getTrustedClientPassword());
        } catch (LoginException e) {
            logger.error("Error", e);
            throw new RuntimeException("Error during system auth");
        }
        AppContext.setSecurityContext(new SecurityContext(systemSession));
        //Search for a user
        try {
            Admin admin = dataManager.load(Admin.class)
                    .query("select e from onlinelibrarysystem_Admin e where e.login=:username and e.active=true")
                    .parameter("username", request.getUsername())
                    .one();
            logger.info("admin" + admin);

            if (!passwordEncryption.checkPassword(admin, request.getPassword())) {
                response.setCode(400);
                response.setMessage("Wrong password");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }

            else if (admin.getChangePasswordAtNextLogon()) {
                response.setCode(410);
                response.setMessage("Change password first time ");
                return new ResponseEntity(response, HttpStatus.GONE);

            } else if (passwordEncryption.checkPassword(admin, request.getPassword())) {
                Otp otp = metadata.create(Otp.class);
                otp.setAdmin((Admin) admin);
                otp.setRedeemed(false);
                otp.setExpiryDate(new Date());
                otp.setOtpAttempts(0);
                dataManager.commit(otp);

                final Otp reload = dataManager.reload(otp, "otp-view");
                //send email OTP
                if (admin.getEmail() != null)
                    logger.info(">>>>>>>>>>>>>..SENDING EMAIL");
                logger.info("otp>>>>>>>>>>>>>>>>>>>>>>" + reload.getOtp());
                emailService.sendEmailAsync(new EmailInfo(admin.getEmail(), "OTP", reload.getOtp()));
                return new ResponseEntity(new TwoFactorResponse(reload.getId().toString()), HttpStatus.ACCEPTED);

            }

        } catch (Exception e) {
            logger.error("Error", e);
        }
        try{
            Librarians librarian = dataManager.load(Librarians.class)
                    .query("select e from onlinelibrarysystem_Librarians e where e.login=:username and e.active=true")
                    .parameter("username",request.getUsername())
                    .one();
            logger.info("librarian"+librarian);

            if (!passwordEncryption.checkPassword(librarian, request.getPassword())) {
                response.setCode(400);
                response.setMessage("Wrong password");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
//            else if(officer.getActionStatus()== Status.UNAPPROVED){
//                response.setCode(401);
//                response.setMessage("User is unapproved");
//                return new ResponseEntity(response,HttpStatus.UNAUTHORIZED);
//            }
            else if(librarian.getChangePasswordAtNextLogon()) {
                response.setCode(410);
                response.setMessage("Change password first time ");
                return new ResponseEntity(response, HttpStatus.GONE);
            }
            else if(passwordEncryption.checkPassword(librarian, request.getPassword())) {
                Otp otp = metadata.create(Otp.class);
                otp.setLibrarian((Librarians) librarian);
                otp.setRedeemed(false);
                otp.setExpiryDate(new Date());
                otp.setOtpAttempts(0);
                dataManager.commit(otp);

                final Otp reload = dataManager.reload(otp, "otp-view");
                //send email OTP
                if (librarian.getEmail() != null)
                    logger.info(">>>>>>>>>>>>>..SENDING EMAIL");
                logger.info("otp>>>>>>>>>>>>>>>>>>>>>>" + reload.getOtp());
                emailService.sendEmailAsync(new EmailInfo(librarian.getEmail(), "OTP", reload.getOtp()));
                return new ResponseEntity(new TwoFactorResponse(reload.getId().toString()), HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            logger.error("Error",e);
        }
        response.setCode(404);
        response.setMessage("No user found with your provided username! try again");
        return new ResponseEntity(response,HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("verify-token")
    ResponseEntity verifyToken(@RequestBody TwoFAVerificationRequest request) {
        ResponseWrapper response = new ResponseWrapper();
        // obtain system session to be able to call middleware services
        WebAuthConfig webAuthConfig = configuration.getConfig(WebAuthConfig.class);
        UserSession systemSession;
        try {
            systemSession = trustedClientService.getSystemSession(webAuthConfig.getTrustedClientPassword());
        } catch (LoginException e) {
            logger.error("Error", e);
            throw new RuntimeException("Error during system auth");
        }
        // set security context
        AppContext.setSecurityContext(new SecurityContext(systemSession));
        Otp otp = dataManager.load(Otp.class)
                .view("otp-view")
                .id(UUID.fromString(request.getCorrelationId())).one();
        if (otp.getRedeemed()) {
            response.setCode(401);
            response.setMessage("otp already used");
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        } else if (!otp.getOtp().equals(request.getOtpCode())) {
            response.setCode(400);
            response.setMessage("Invalid Otp");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else if (otp.getOtp().equals(request.getOtpCode())) {
            otp.setRedeemed(true);
            //Issue token to user
            if (otp.getAdmin() != null) {
                OAuthTokenIssuer.OAuth2AccessTokenResult tokenResult =
                        oAuthTokenIssuer.issueToken(otp.getAdmin().getLogin(), messageTools.getDefaultLocale(), Collections.emptyMap());
                OAuth2AccessToken accessToken = tokenResult.getAccessToken();

                dataManager.commit(otp);
                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
                headers.set(HttpHeaders.PRAGMA, "no-cache");
                return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);
            }
            if (otp.getLibrarian() != null) {
                otp.setRedeemed(true);
                //Issue token to user
                OAuthTokenIssuer.OAuth2AccessTokenResult tokenResult =
                        oAuthTokenIssuer.issueToken(otp.getLibrarian().getLogin(), messageTools.getDefaultLocale(), Collections.emptyMap());
                OAuth2AccessToken accessToken = tokenResult.getAccessToken();

                dataManager.commit(otp);
                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
                headers.set(HttpHeaders.PRAGMA, "no-cache");
                return new ResponseEntity<>(accessToken, headers, HttpStatus.OK);


            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}