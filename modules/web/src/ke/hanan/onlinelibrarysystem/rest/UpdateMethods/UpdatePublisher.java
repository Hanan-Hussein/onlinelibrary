package ke.hanan.onlinelibrarysystem.rest.UpdateMethods;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Security;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.UUID;
@RestController

@RequestMapping("v2/update")
public class UpdatePublisher {

    @Inject
    private DataManager dataManager;
    @Inject
    private Logger log;
    @Inject
    private Security security;
    @PutMapping("/publisher/{id}")
    public ResponseWrapper UpdatePublisher(@RequestBody Publisher pub, @PathVariable String id){
        ResponseWrapper response = new ResponseWrapper();

        try{
            if(!security.isSpecificPermitted("app.app.updatePublisher")){
                response.setCode(401);
                response.setMessage("User doesn't have permissions to Update Subject");
                return response;
            }
            Publisher ps = dataManager.load(Publisher.class)
                    .query("select e from onlinelibrarysystem_Publisher e where e.id=:id")
                    .parameter("id", UUID.fromString(id))
                    .one();
            ps.setPublisherName(pub.getPublisherName());
            ps.setDescription(pub.getDescription());
            ps.setCode(pub.getCode());

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


