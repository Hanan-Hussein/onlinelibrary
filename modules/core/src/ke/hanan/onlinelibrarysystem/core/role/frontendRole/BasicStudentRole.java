package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.addon.restapi.entity.AccessToken;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.*;

@Role(name = BasicStudentRole.NAME, securityScope = "REST", isDefault = true)
public class BasicStudentRole extends AnnotatedRoleDefinition {
    public final static String NAME = "BasicStudentRole";

    @EntityAccess(entityClass = Author.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Publisher.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Subject.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Book.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = User.class, operations = EntityOp.UPDATE)
    @EntityAccess(entityClass = FileDescriptor.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Otp.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = AccessToken.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Author.class, view = "*")
    @EntityAttributeAccess(entityClass = Publisher.class, view = "*")
    @EntityAttributeAccess(entityClass = Subject.class, view = "*")
    @EntityAttributeAccess(entityClass = Book.class, view = "*")
    @EntityAttributeAccess(entityClass = FileDescriptor.class, modify = "*")
    @EntityAttributeAccess(entityClass = Otp.class, modify = "*")
    @EntityAttributeAccess(entityClass = AccessToken.class, modify = "*")
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
