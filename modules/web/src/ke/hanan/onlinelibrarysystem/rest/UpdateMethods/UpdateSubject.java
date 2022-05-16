package ke.hanan.onlinelibrarysystem.rest.UpdateMethods;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.UUID;
@RestController

@RequestMapping("v2/update")

public class UpdateSubject {
    @Inject
    private DataManager dataManager;
    @Inject
    private Logger log;
    @Inject
    private Security security;
    @PutMapping("/subject/{id}")
    public ResponseWrapper UpdateCategory(@RequestBody Subject category, @PathVariable String id){
        ResponseWrapper response = new ResponseWrapper();

        try{
            if(!security.isSpecificPermitted("app.app.UpdateSubject")){
                response.setCode(401);
                response.setMessage("User doesn't have permissions to Update Subject");
                return response;
            }
            Subject ps = dataManager.load(Subject.class)
                    .query("select e from onlinelibrarysystem_Subject e where e.id=:id")
                    .parameter("id", UUID.fromString(id))
                    .one();
            ps.setSubjectName(category.getSubjectName());
            ps.setDescription(category.getDescription());
            ps.setCode(category.getCode());
            dataManager.commit(ps);
            response.setMessage(" You've Successfully updated");

        }catch (Exception e){
            log.error("Error", e);
            response.setCode(409);
            response.setMessage("unauthorized, Authorization required");

        }
        return response;

    }


}
