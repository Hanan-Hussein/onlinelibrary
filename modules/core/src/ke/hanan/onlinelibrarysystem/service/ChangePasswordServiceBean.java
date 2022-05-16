package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import ke.hanan.onlinelibrarysystem.wrappers.ChangePasswordWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(ChangePasswordService.NAME)
public class ChangePasswordServiceBean implements ChangePasswordService {
    @Inject
    private DataManager dataManager;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Override
    public ResponseWrapper ChangePassword(ChangePasswordWrapper changePasswordWrapper) {
        ResponseWrapper response = new ResponseWrapper();

        User currentUser = userSessionSource.getUserSession().getUser();

        if(!passwordEncryption.checkPassword(currentUser,changePasswordWrapper.getOldPass())){
            response.setCode(400);
            response.setMessage("Wrong Old password, re-check and try again");
        }
//        else if(!changePasswordWrapper.getNewPassword().equals(changePasswordWrapper.getConfirmNewPassword())) {
//            response.setCode(400);
//            response.setMessage("New password and confirm new password do not match");
//        }
        else if (passwordEncryption.checkPassword(currentUser,changePasswordWrapper.getNewPassword())){
            response.setCode(400);
            response.setMessage("Your old password and new password can't be a match");
        }else {
            currentUser.setPassword(passwordEncryption.
                    getPasswordHash(currentUser.getId(),changePasswordWrapper.getNewPassword()));
            dataManager.commit(currentUser);
            response.setCode(200);
            response.setMessage(" You've Successfully changed your password");

        }

        return response;
    }
}