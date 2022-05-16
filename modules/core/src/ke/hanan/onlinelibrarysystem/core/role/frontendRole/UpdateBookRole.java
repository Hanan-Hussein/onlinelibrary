package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.Book;

@Role(name = UpdateBookRole.NAME, securityScope = "REST")
public class UpdateBookRole extends AnnotatedRoleDefinition {
    public final static String NAME = "updateBook";

    @EntityAccess(entityClass = Book.class, operations = EntityOp.UPDATE)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
}
