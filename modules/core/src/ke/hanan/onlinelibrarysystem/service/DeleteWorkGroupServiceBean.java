package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(DeleteWorkGroupService.NAME)
public class DeleteWorkGroupServiceBean implements DeleteWorkGroupService {
    @Inject
    private Security security;
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;

    @Override
    public ResponseWrapper WorkGroups(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.DeleteStudent")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Delete Student");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (String id : disable.getIds()) {
                WorkGroups groups = em
                        .createQuery("select e from onlinelibrarysystem_WorkGroups e where e.id=:id", WorkGroups.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (groups == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist" + id);
                    response.setData(null);
                    return response;
                }
                em.remove(groups);
                dataManager.commit(groups);
            }
            tx.commit();
        }
        return response;

    }
}