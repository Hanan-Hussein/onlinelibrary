package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailInfo;
import ke.hanan.onlinelibrarysystem.entity.BookRequest;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateBookRequestService.NAME)
public class CreateBookRequestServiceBean implements CreateBookRequestService {
    @Inject
    private Persistence persistence;
    @Inject
    private EmailService emailService;
    @Override
    public ResponseWrapper createBookRequest(BookRequest request) {
        ResponseWrapper response = new ResponseWrapper();

        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(request);

            emailService.sendEmailAsync(new EmailInfo(request.getEmail(), "Book Request", "You will be notified when the book is added "));

            tx.commit();
            response.setData(request);

        }
        return response;
    }
}