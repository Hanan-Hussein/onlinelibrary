package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.SessionAttribute;
import com.haulmont.cuba.security.entity.SessionLogEntry;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;

@Role(name = SessionReportRole.NAME, securityScope = "REST")
public class SessionReportRole extends AnnotatedRoleDefinition {
    public final static String NAME = "SessionReport";

    @EntityAccess(entityClass = SessionLogEntry.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = SessionAttribute.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = SessionLogEntry.class, view = "*")
    @EntityAttributeAccess(entityClass = SessionAttribute.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
