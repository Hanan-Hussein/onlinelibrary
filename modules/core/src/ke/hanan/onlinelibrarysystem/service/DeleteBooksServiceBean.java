package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Book;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.UUID;

@Service(DeleteBooksService.NAME)
public class DeleteBooksServiceBean implements DeleteBooksService {

    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private Security security;
    @Override
    public ResponseWrapper DeleteBooks(ApproveWrapper disable) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.deletebook")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Delete Book");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            for (String id : disable.getIds()) {
                Book book = em
                        .createQuery("select e from onlinelibrarysystem_Book e where e.id=:id", Book.class)
                        .setParameter("id", UUID.fromString(id))
                        .getFirstResult();
                if (book == null) {
                    response.setCode(404);
                    response.setMessage("Record with id assigned does not exist" + id);
                    response.setData(null);
                    return response;
                }
                em.remove(book);
                dataManager.commit(book);
            }
            tx.commit();
        }
        return response;

    }
}
