package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(DisableUserService.NAME)
public class DisableUserServiceBean implements DisableUserService {
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private Security security;
    @Override
    public ResponseWrapper disableUser(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.disableAdmin")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Disable Admin");
            return response;
        }
        try (
                Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();

            for (String id : disable.getIds()) {
                Admin user = em
                        .createQuery("select e from onlinelibrarysystem_Admin e where e.id = :id", Admin.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (user == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist " + id);
                    response.setData(null);
                    return response;
                }
//
                user.setActive(false);

                dataManager.commit(user);

            }
            tx.commit();
        }
        return response;
    }
}