package ke.hanan.onlinelibrarysystem.core.role;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.*;

@Role(name = AnonymousRole.NAME, securityScope = "REST")
public class AnonymousRole extends AnnotatedRoleDefinition {
    public final static String NAME = "AnonymousRole";

    @EntityAccess(entityClass = Students.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Subject.class, operations = {EntityOp.READ, EntityOp.CREATE})
    @EntityAccess(entityClass = JournalArticle.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Book.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Author.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Students.class, view = "*")
    @EntityAttributeAccess(entityClass = Subject.class, view = "*")
    @EntityAttributeAccess(entityClass = JournalArticle.class, view = "*")
    @EntityAttributeAccess(entityClass = Book.class, view = "*")
    @EntityAttributeAccess(entityClass = Author.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = {"cuba.restApi.enabled", "cuba.restApi.fileUpload.enabled"})
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
