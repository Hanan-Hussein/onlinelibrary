package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateMasterDataService.NAME)
public class CreateMasterDataServiceBean implements CreateMasterDataService {
    @Inject
    private Persistence persistence;
    @Inject
    private Security security;

    @Override
    public ResponseWrapper createPublisher(Publisher publisher) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.createPublisher")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Create Publisher");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            String code = publisher.getCode();
            Publisher publisher1 = em.createQuery("select e from onlinelibrarysystem_Publisher e where e.code=:code", Publisher.class)
                    .setParameter("code", code).getFirstResult();
            Publisher publisher2 = em.createQuery("select e from onlinelibrarysystem_Publisher e where e.publisherName=:publisherName", Publisher.class)
                    .setParameter("publisherName", publisher.getPublisherName()).getFirstResult();

            if (publisher1 != null) {
                response.setMessage("Publisher Code already exists");
                response.setCode(409);
                return response;
            } else if (publisher2 != null) {
                response.setMessage("Name of the publisher already exists");
                response.setCode(409);
                return response;
            }
            persistence.getEntityManager().persist(publisher);
            response.setData(publisher);
            tx.commit();
        }
        return response;
    }

    @Override
    public ResponseWrapper createSubject(Subject subject) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.createSubject")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Create Subject");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            String code = subject.getCode();
            Subject subject1 = em.createQuery("select e from onlinelibrarysystem_Subject e where e.code=:code", Subject.class)
                    .setParameter("code", code).getFirstResult();
            Subject subject2 = em.createQuery("select e from onlinelibrarysystem_Subject e where e.subjectName=:subjectName", Subject.class)
                    .setParameter("subjectName", subject.getSubjectName()).getFirstResult();

            if (subject1 != null) {
                response.setMessage("Subject Code already exists");
                response.setCode(409);
                return response;
            } else if (subject2 != null) {
                response.setMessage("Subject Name already exists");
                response.setCode(409);
                return response;
            }
            persistence.getEntityManager().persist(subject);
            response.setData(subject);
            tx.commit();
        }
        return response;
    }
}