package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.Admin;

@Role(name = DisableAdminRole.NAME, securityScope = "REST")
public class DisableAdminRole extends AnnotatedRoleDefinition {
    public final static String NAME = "DisableAdmin";

    @SpecificAccess(permissions = "app.disableAdmin")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }

    @EntityAccess(entityClass = Admin.class, operations = {EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
}
