package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

@Service(CreateLibrarianService.NAME)
public class CreateLibrarianServiceBean implements CreateLibrarianService {
    private static final Logger log = LoggerFactory.getLogger(CreateLibrarianServiceBean.class);

    Map<String, Object> errors;
    @Inject
    private Persistence persistence;

    @Inject
    private Metadata metadata;
    @Inject
    private Security security;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private EmailService emailService;

    @Override
    public ResponseWrapper createLibrarian(Librarians librarians) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.createLibrarian")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Create Librarians");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {

            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(librarians);

            Librarians librarians1 = em.createQuery("select e from onlinelibrarysystem_Librarians e where e.email=:email", Librarians.class)
                    .setParameter("email", librarians.getEmail()).getFirstResult();
            Librarians librarians2 = em.createQuery("select e from onlinelibrarysystem_Librarians e where e.phoneNumber=:phoneNumber", Librarians.class)
                    .setParameter("phoneNumber", librarians.getPhoneNumber()).getFirstResult();
            Librarians librarians3 = em.createQuery("select e from onlinelibrarysystem_Librarians e where e.nationalId=:nationalId", Librarians.class)
                    .setParameter("nationalId", librarians.getNationalId()).getFirstResult();

            if (librarians1 != null) {
                response.setMessage("Email already exists");
                response.setCode(409);
                return response;
            }
            if (librarians2 != null) {
                response.setCode(409);
                response.setMessage("PhoneNumber already exists");
                return response;
            }
            if (librarians3 != null) {
                response.setCode(409);
                response.setMessage("National Id already exists");
            }

            String pass = passwordEncryption.generateRandomPassword();
            librarians.setPassword(passwordEncryption.getPasswordHash(librarians.getId(), pass));
            log.info(pass);
            final String passEmail = " Welcome to Apollo Library Use the following login credentials: username "+librarians.getEmail() +" password: "+pass;
            emailService.sendEmailAsync(new EmailInfo(librarians.getEmail(), "LOGIN CREDENTIALS", passEmail));

            tx.commit();
            response.setData(librarians);

        }
        return response;

    }
}
