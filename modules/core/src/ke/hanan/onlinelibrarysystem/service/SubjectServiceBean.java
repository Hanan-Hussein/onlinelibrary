package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(SubjectService.NAME)
public class SubjectServiceBean implements SubjectService {

    @Inject
    private Persistence persistence;

    @Override
    public ResponseWrapper createSubject(Subject subject) {
        ResponseWrapper response = new ResponseWrapper();

        try(Transaction tx = persistence.createTransaction()){

            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(subject);

            Subject subject1 = em.createQuery("select e from onlinelibrarysystem_Subject e where e.code=:code",Subject.class)
                    .setParameter("code",subject.getCode())
                    .getFirstResult();

            if(subject1!=null){
                response.setCode(409);
                response.setMessage("Subject code already exists");
                return response;
            }
        tx.commit();
            response.setData(subject);
        }

        return response;
    }
}