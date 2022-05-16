package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = UpdateStudentRole.NAME, securityScope = "REST")
public class UpdateStudentRole extends AnnotatedRoleDefinition {
    public final static String NAME = "UpdateStudent";

    @SpecificAccess(permissions = "app.UpdateStudent")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
