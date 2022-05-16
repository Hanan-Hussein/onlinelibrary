package ke.hanan.onlinelibrarysystem.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(CreateWorkGroupService.NAME)
public class CreateWorkGroupServiceBean implements CreateWorkGroupService {
    @Inject
    Persistence persistence;
    @Inject
    DataManager dataManager;
    @Inject
    private Security security;

    @Override
    public ResponseWrapper createWorkGroup(WorkGroups workGroups) {
        ResponseWrapper responseWrapper= new ResponseWrapper();
//
//        if(!security.isSpecificPermitted("app.CreateWorkGroups")){
//            responseWrapper.setCode(401);
//            responseWrapper.setMessage("User doesn't have permissions to Create WorkGroups");
//            return responseWrapper;
//        }
        try(Transaction tx=persistence.createTransaction()){
            EntityManager em= persistence.getEntityManager();
            WorkGroups wG=em
                    .createQuery("select e from onlinelibrarysystem_WorkGroups e  where e.name = :name", WorkGroups.class)
                    .setParameter("name",workGroups.getName())
                    .getFirstResult();


            // Validate code
            if (wG != null) {
                responseWrapper.setMessage(" Work Group Name already exists");
                responseWrapper.setCode(409);
                responseWrapper.setData(null);
                return responseWrapper;
            }

            em.persist(workGroups);
            workGroups.getWorkgroupRoles().forEach(workGroupRoles -> {
                workGroupRoles.setWorkGroup(workGroups);
                em.persist(workGroupRoles);
            });
            responseWrapper.setData(workGroups);

            tx.commit();
        }
        return responseWrapper;
    }
}
