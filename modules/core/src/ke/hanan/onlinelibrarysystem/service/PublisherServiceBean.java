package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(PublisherService.NAME)
public class PublisherServiceBean implements PublisherService {

    @Inject
    private Persistence persistence;

    @Override
    public ResponseWrapper createPublisher(Publisher publisher) {
        ResponseWrapper response= new ResponseWrapper();

        try (Transaction tx= persistence.createTransaction()){
            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(publisher);

            Publisher publisher1= em.createQuery("select e from onlinelibrarysystem_Publisher e where e.publisherName=:publisherName",Publisher.class)
                    .setParameter("publisherName",publisher.getPublisherName())
                    .getFirstResult();

            if(publisher1!=null){
                response.setCode(409);
                response.setMessage("Publisher Name already exists");
                return response;
            }
            tx.commit();
            response.setData(publisher);

        }
        return response;
    }
}