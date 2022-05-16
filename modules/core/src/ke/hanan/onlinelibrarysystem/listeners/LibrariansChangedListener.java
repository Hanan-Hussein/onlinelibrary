package ke.hanan.onlinelibrarysystem.listeners;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.app.events.EntityPersistingEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.role.RolesService;
import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("onlinelibrarysystem_LibrariansChangedListener")
public class LibrariansChangedListener {
    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;
    @Inject
    private RolesService rolesService;
    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private Logger log;

    @EventListener
    public void beforeCommit(EntityPersistingEvent<Librarians> event) {
        Librarians newlibrarians=event.getEntity();

        Group group;
        try {
            group = dataManager.load(Group.class)
                    .query("select g from sec$Group g where g.name = :group")
                    .parameter("group", "librarian")
                    .one();
        } catch (IllegalStateException e) {
            Group parent = dataManager.load(Group.class)
                    .query("select g from sec$Group g where g.name = :group")
                    .parameter("group", "Company")
                    .one();

            group = metadata.create(Group.class);
            group.setName("librarian");
            group.setParent(parent);
            group   = dataManager.commit(group);

        }
        newlibrarians.setGroup(group);
        newlibrarians.setLogin(newlibrarians.getEmail());
        String pass = passwordEncryption.generateRandomPassword();
        log.info(pass);
        newlibrarians.setPassword(passwordEncryption.getPasswordHash(newlibrarians.getId(), pass));
        newlibrarians.setChangePasswordAtNextLogon(true);
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAdminAfterCommit(EntityChangedEvent<Librarians, UUID> event) {
        Librarians user =dataManager.load(Librarians.class).id(event.getEntityId().getValue())
                .view("librarian-group-view")
                .one();
        for (WorkGroups workGroups: user.getWorkGroups()){
            for (WorkGroupRoles workGroupRoles: workGroups.getWorkgroupRoles()){
                UserRole userRole = metadata.create(UserRole.class);
                userRole.setUser(user);
                log.warn(workGroupRoles.getRole());
                userRole.setRoleName(workGroupRoles.getRole());
                userRole.setRoleDefinition(rolesService.getRoleDefinitionByName(workGroupRoles.getRole()));
                dataManager.commit(userRole);

            }
        }

    }
}