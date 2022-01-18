package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.PasswordEncryption;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateStudentService.NAME)
public class CreateStudentServiceBean implements CreateStudentService {

    @Inject
    private Persistence persistence;
    @Inject
    private PasswordEncryption passwordEncryption;

    @Override
    public ResponseWrapper createStudent(Students students) {
        ResponseWrapper response = new ResponseWrapper();

        try (Transaction tx = persistence.createTransaction()){
            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(students);

           Students students1= em.createQuery("select e from onlinelibrarysystem_Students e where e.email=:email",Students.class)
                    .setParameter("email",students.getEmail())
                    .getFirstResult();
            if(students1 !=null){
                response.setCode(409);
                response.setMessage("Student already exists");
                return response;
            }
            String pass = students.getPassword();
            students.setPassword(passwordEncryption.getPasswordHash(students.getId(),pass));

            tx.commit();
            response.setData(students);

        }
        return response;
    }
}