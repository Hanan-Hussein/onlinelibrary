package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.Students;

@Role(name = ReadStudentRole.NAME, securityScope = "REST")
public class ReadStudentRole extends AnnotatedRoleDefinition {
    public final static String NAME = "ReadStudent";

    @EntityAccess(entityClass = Students.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Students.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
