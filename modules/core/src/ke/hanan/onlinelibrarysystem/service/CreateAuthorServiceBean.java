package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.PasswordEncryption;
import ke.hanan.onlinelibrarysystem.entity.Author;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateAuthorService.NAME)
public class CreateAuthorServiceBean implements CreateAuthorService {
    @Inject
    private Persistence persistence;


    @Override
    public ResponseWrapper createAuthor(Author author) {
        ResponseWrapper responseWrapper=new ResponseWrapper();
        try(Transaction tx = persistence.createTransaction()){
            EntityManager em= persistence.getEntityManager();
            persistence.getEntityManager().persist(author);

            tx.commit();
            responseWrapper.setData(author);
        }
        return responseWrapper;
    }
}