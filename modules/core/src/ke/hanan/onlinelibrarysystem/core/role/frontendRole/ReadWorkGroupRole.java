package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;

@Role(name = ReadWorkGroupRole.NAME, securityScope = "REST")
public class ReadWorkGroupRole extends AnnotatedRoleDefinition {
    public final static String NAME = "ReadWorkGroup";

    @EntityAccess(entityClass = UserRole.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = WorkGroups.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = WorkGroupRoles.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = UserRole.class, view = "*")
    @EntityAttributeAccess(entityClass = WorkGroups.class, view = "*")
    @EntityAttributeAccess(entityClass = WorkGroupRoles.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
