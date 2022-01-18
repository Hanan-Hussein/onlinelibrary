package ke.hanan.onlinelibrarysystem.listeners;

import com.haulmont.cuba.core.app.events.EntityPersistingEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.security.entity.Group;
import ke.hanan.onlinelibrarysystem.entity.Students;

import java.util.UUID;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("onlinelibrarysystem_StudentsChangedListener")
public class StudentsChangedListener {

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;
    @Inject
    private PasswordEncryption passwordEncryption;

    @EventListener
    public void beforeCommit(EntityPersistingEvent<Students> event) {
        Students students = event.getEntity();
        Group group;

        try{
            group= dataManager.load(Group.class)
                    .query("select e from sec$Group e where e.name =:group")
                    .parameter("group","student")
                    .one();
        }catch (IllegalStateException e)
        {
           Group parent= dataManager.load(Group.class)
                    .query("select e from sec$Group e where e.name =:group")
                    .parameter("group", "Company")
                    .one();
            group = metadata.create(Group.class);
            group.setParent(parent);
            group.setName("student");
            group= dataManager.commit(group);
        }
        students.setLogin(students.getEmail());
        students.setGroup(group);
    }
}