package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(UserInfoService.NAME)
public class UserInfoServiceBean implements UserInfoService {
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private DataManager dataManager;
    @Override
    public Admin UserInfo() {
        User currentUser = userSessionSource.getUserSession().getUser();
        return dataManager.load(Admin.class)
                .query("select e from onlinelibrarysystem_Admin e where e.login=:username and e.active=true")
                .parameter("username", currentUser.getLogin())
                .view("admin-view")
                .one();
    }
}