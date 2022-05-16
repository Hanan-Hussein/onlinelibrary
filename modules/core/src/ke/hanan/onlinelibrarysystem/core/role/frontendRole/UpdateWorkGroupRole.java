package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
import ke.hanan.onlinelibrarysystem.entity.WorkGroups;

@Role(name = UpdateWorkGroupRole.NAME, securityScope = "REST")
public class UpdateWorkGroupRole extends AnnotatedRoleDefinition {
    public final static String NAME = "UpdateWorkGroup";

    @EntityAccess(entityClass = WorkGroups.class, operations = EntityOp.UPDATE)
    @EntityAccess(entityClass = WorkGroupRoles.class, operations = EntityOp.UPDATE)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @SpecificAccess(permissions = "app.updateWorkgroups")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
