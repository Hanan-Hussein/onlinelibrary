package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;

@Role(name = CreateWorkGroupsRole.NAME, securityScope = "REST")
public class CreateWorkGroupsRole extends AnnotatedRoleDefinition {
    public final static String NAME = "CreateWorkGroups";

    @EntityAccess(entityClass = UserRole.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @EntityAccess(entityClass = WorkGroups.class, operations = EntityOp.CREATE)
    @EntityAccess(entityClass = WorkGroupRoles.class, operations = {EntityOp.CREATE, EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = UserRole.class, view = "*")
    @EntityAttributeAccess(entityClass = WorkGroupRoles.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = "app.CreateWorkGroups")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
