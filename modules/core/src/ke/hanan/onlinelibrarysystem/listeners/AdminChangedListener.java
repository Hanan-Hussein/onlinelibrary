package ke.hanan.onlinelibrarysystem.listeners;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.app.events.EntityPersistingEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.app.role.RolesHelper;
import com.haulmont.cuba.security.entity.Group;

import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.role.RolesService;
import ke.hanan.onlinelibrarysystem.entity.Admin;

import java.util.UUID;

import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("onlinelibrarysystem_AdminChangedListener")
public class AdminChangedListener {
    @Inject
    private DataManager dataManager;

    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private EmailService emailService;
    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private Metadata metadata;
    @Inject
    private RolesHelper rolesHelper;
    @Inject
    private RolesService rolesService;
    @Inject
    private Persistence persistence;
    @Inject
    private Logger log;

    @EventListener
    public void beforeCommit(EntityPersistingEvent<Admin> event) {
        Admin newAdmin=event.getEntity();

        Group group;
        try {
            group = dataManager.load(Group.class)
                    .query("select g from sec$Group g where g.name = :group")
                    .parameter("group", "Admin")
                    .one();
        } catch (IllegalStateException e) {
            Group parent = dataManager.load(Group.class)
                    .query("select g from sec$Group g where g.name = :group")
                    .parameter("group", "Company")
                    .one();

            group = metadata.create(Group.class);
            group.setName("Admin");
            group.setParent(parent);
            group   = dataManager.commit(group);

        }
        newAdmin.setGroup(group);
        newAdmin.setLogin(newAdmin.getEmail());
        String pass = passwordEncryption.generateRandomPassword();
        log.info(pass);
        newAdmin.setPassword(passwordEncryption.getPasswordHash(newAdmin.getId(), pass));
        newAdmin.setChangePasswordAtNextLogon(true);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onAdminAfterCommit(EntityChangedEvent<Admin, UUID> event) {
        Admin user =dataManager.load(Admin.class).id(event.getEntityId().getValue())
                .view("work-group-view")
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