package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Book;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateBooksService.NAME)

public class CreateBooksServiceBean implements CreateBooksService {
    @Inject
    private Persistence persistence;
    @Inject
    private Security security;
    @Inject
    private PasswordEncryption passwordEncryption;

    @Override
    public ResponseWrapper createBooks(Book book) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.createBook")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Add Book");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(book);

            Book book1 = em.createQuery("select e from onlinelibrarysystem_Book e where e.isbn=:isbn", Book.class)
                    .setParameter("isbn", book.getIsbn())
                    .getFirstResult();
            if (book1 != null) {
                response.setCode(409);
                response.setMessage("Book already exists");
                return response;
            }


            tx.commit();
            response.setData(book);

        }
        return response;
    }
}
