package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.Book;

@Role(name = DeleteBookRole.NAME, securityScope = "REST")
public class DeleteBookRole extends AnnotatedRoleDefinition {
    public final static String NAME = "DeleteBook";

    @EntityAccess(entityClass = Book.class, operations = EntityOp.DELETE)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @SpecificAccess(permissions = "app.deletebook")
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
