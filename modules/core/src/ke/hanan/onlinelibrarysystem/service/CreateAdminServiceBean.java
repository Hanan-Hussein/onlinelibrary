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
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Map;

@Service(CreateAdminService.NAME)
public class CreateAdminServiceBean implements CreateAdminService {
    private static final Logger log = LoggerFactory.getLogger(CreateAdminServiceBean.class);

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
    public ResponseWrapper createAdmin(Admin admin) {
        ResponseWrapper response = new ResponseWrapper();
        if(!security.isSpecificPermitted("app.CreateAdmin")){
            response.setCode(401);
            response.setMessage("User doesn't have permissions to Create Admin");
            return response;
        }
        try (Transaction tx = persistence.createTransaction()) {

            EntityManager em = persistence.getEntityManager();
            persistence.getEntityManager().persist(admin);

            Admin admin1 = em.createQuery("select e from onlinelibrarysystem_Admin e where e.email=:email", Admin.class)
                    .setParameter("email", admin.getEmail()).getFirstResult();
            Admin admin2 = em.createQuery("select e from onlinelibrarysystem_Admin e where e.phoneNumber=:phoneNumber", Admin.class)
                    .setParameter("phoneNumber", admin.getPhoneNumber()).getFirstResult();
            Admin admin3 = em.createQuery("select e from onlinelibrarysystem_Admin e where e.nationalId=:nationalId", Admin.class)
                    .setParameter("nationalId", admin.getNationalId()).getFirstResult();

            if(admin1!=null){
                response.setMessage("Email already exists");
                response.setCode(409);
                return response;
            }
            if(admin2!=null){
                response.setCode(409);
                response.setMessage("PhoneNumber already exists");
                return response;
            }
            if (admin3 !=null){
                response.setCode(409);
                response.setMessage("National Id already exists");
            }

            String pass = passwordEncryption.generateRandomPassword();
            admin.setPassword(passwordEncryption.getPasswordHash(admin.getId(), pass));
            log.info(pass);
            final String passEmail = " Welcome to Ecurfew System Use the following login credentials: username "+admin.getEmail() +" password: "+pass;
            emailService.sendEmailAsync(new EmailInfo(admin.getEmail(), "LOGIN CREDENTIALS", passEmail));

            tx.commit();
            response.setData(admin);

        }
        return response;

    }
}