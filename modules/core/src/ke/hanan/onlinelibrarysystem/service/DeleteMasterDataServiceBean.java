package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.core.global.UserSessionSource;
import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(DeleteMasterDataService.NAME)
public class DeleteMasterDataServiceBean implements DeleteMasterDataService {
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Security security;

    @Override
    public ResponseWrapper DeleteSubject(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.deleteSubject")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Delete Subject");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (String id : disable.getIds()) {
                Subject subject = em
                        .createQuery("select e from onlinelibrarysystem_Subject e where e.id=:id", Subject.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (subject == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist" + id);
                    response.setData(null);
                    return response;
                }
                em.remove(subject);
                dataManager.commit(subject);
            }
            tx.commit();
        }
        return response;

    }

    @Override
    public ResponseWrapper DeletePublisher(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.deletePublisher")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Delete Publisher");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (String id : disable.getIds()) {
                Publisher publisher = em
                        .createQuery("select e from onlinelibrarysystem_Publisher e where e.id=:id", Publisher.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (publisher == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist" + id);
                    response.setData(null);
                    return response;
                }
                em.remove(publisher);
                dataManager.commit(publisher);
            }
            tx.commit();
        }
        return response;

    }
}