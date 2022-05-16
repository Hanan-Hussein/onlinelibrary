package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Book;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(DeleteLibrarianService.NAME)
public class DeleteLibrarianServiceBean implements DeleteLibrarianService {
    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private Security security;
    @Override
    public ResponseWrapper DeleteLibrarian(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.deleteLibrarian")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Delete Librarian");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (String id : disable.getIds()) {
                Librarians librarians = em
                        .createQuery("select e from onlinelibrarysystem_Librarians e where e.id=:id", Librarians.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (librarians == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist" + id);
                    response.setData(null);
                    return response;
                }
                em.remove(librarians);
                dataManager.commit(librarians);
            }
            tx.commit();
        }
        return response;

    }
}
