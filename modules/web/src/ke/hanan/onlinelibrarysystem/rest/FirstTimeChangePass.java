package ke.hanan.onlinelibrarysystem.rest;

import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.TrustedClientService;
import com.haulmont.cuba.security.global.LoginException;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.auth.WebAuthConfig;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.wrappers.FirstTimeChangePassword;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("v2/firstTimeChangePassword")
public class FirstTimeChangePass {
    @Inject
    private DataManager dataManager;
    @Inject
    private Logger log;
    @Inject
    private Security security;
    @Inject
    PasswordEncryption passwordEncryption;
    @Inject
    private Configuration configuration;
    @Inject
    private TrustedClientService loginService;

    @PostMapping("change-pass")
    ResponseEntity changePassword(@RequestBody FirstTimeChangePassword firstTimeChangePassword) {
        WebAuthConfig webAuthConfig = configuration.getConfig(WebAuthConfig.class);
        UserSession systemSession;
        try {
            systemSession = loginService.getSystemSession(webAuthConfig.getTrustedClientPassword());
        } catch (LoginException e) {
            log.error("Error", e);
            throw new RuntimeException("Error during system auth");
        }

        // set security context
        AppContext.setSecurityContext(new SecurityContext(systemSession));
        ResponseWrapper response = new ResponseWrapper();
        try {
            Admin user = dataManager.load(Admin.class)
                    .query("select e from onlinelibrarysystem_Admin e where e.login=:username and e.active=true")
                    .parameter("username", firstTimeChangePassword.getEmail())
                    .one();
            user=dataManager.reload(user,"_local");
            if(user.getChangePasswordAtNextLogon()){
                if(!firstTimeChangePassword.getNewPassword().equals(firstTimeChangePassword.getConfirmPassword())){
                    response.setCode(400);
                    response.setMessage("New password and Confirm New Password Do not Match");
                }
                user.setPassword(passwordEncryption.getPasswordHash
                        (user.getId(),firstTimeChangePassword.getNewPassword()));
                user.setChangePasswordAtNextLogon(false);
                dataManager.commit(user);
                response.setCode(200);
                response.setMessage("Password has been changed successfully");
                return new ResponseEntity(response, HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            log.error("Error",e);
        }
        try {
            Librarians librarians = dataManager.load(Librarians.class)
                    .query("select e from onlinelibrarysystem_Librarians e where e.login=:username and e.active=true")
                    .parameter("username", firstTimeChangePassword.getEmail())
                    .one();
            librarians=dataManager.reload(librarians,"_local");
            if(librarians.getChangePasswordAtNextLogon()){
                if(!firstTimeChangePassword.getNewPassword().equals(firstTimeChangePassword.getConfirmPassword())){
                    response.setCode(400);
                    response.setMessage("New password and Confirm New Password Do not Match");
                }
                librarians.setPassword(passwordEncryption.getPasswordHash
                        (librarians.getId(),firstTimeChangePassword.getNewPassword()));
                librarians.setChangePasswordAtNextLogon(false);
                dataManager.commit(librarians);
                response.setCode(200);
                response.setMessage("Password has been changed successfully");
                return new ResponseEntity(response, HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            log.error("Error",e);
        }

        return null;
    }
    }
