package ke.hanan.onlinelibrarysystem.rest;

import com.haulmont.addon.restapi.api.auth.OAuthTokenIssuer;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.MessageTools;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.TrustedClientService;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.rest.Model.LoginModel;
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
    private Logger logger;
    @Inject
    private OAuthTokenIssuer oAuthTokenIssuer;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private MessageTools messageTools;

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
                response.setCode(40);
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
}
