package ke.hanan.onlinelibrarysystem.core.role.frontendRole;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.SpecificAccess;
import com.haulmont.cuba.security.entity.*;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;
import ke.hanan.onlinelibrarysystem.entity.*;

@Role(name = AnonymousRole.NAME, securityScope = "REST")
public class AnonymousRole extends AnnotatedRoleDefinition {
    public final static String NAME = "AnonymousRole";

    @EntityAccess(entityClass = Admin.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = User.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = UserRole.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = com.haulmont.cuba.security.entity.Role.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = WorkGroups.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = WorkGroupRoles.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Librarians.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Publisher.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = EntityLogItem.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = SessionLogEntry.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = FileDescriptor.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Students.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Subject.class, operations = {EntityOp.READ, EntityOp.CREATE})
    @EntityAccess(entityClass = JournalArticle.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Book.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Author.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Admin.class, modify = "*")
    @EntityAttributeAccess(entityClass = User.class, modify = "*")
    @EntityAttributeAccess(entityClass = UserRole.class, modify = "*")
    @EntityAttributeAccess(entityClass = com.haulmont.cuba.security.entity.Role.class, modify = "*")
    @EntityAttributeAccess(entityClass = WorkGroups.class, modify = "*")
    @EntityAttributeAccess(entityClass = WorkGroupRoles.class, modify = "*")
    @EntityAttributeAccess(entityClass = Librarians.class, modify = "*")
    @EntityAttributeAccess(entityClass = Publisher.class, modify = "*")
    @EntityAttributeAccess(entityClass = EntityLogItem.class, modify = "*")
    @EntityAttributeAccess(entityClass = SessionLogEntry.class, modify = "*")
    @EntityAttributeAccess(entityClass = FileDescriptor.class, modify = "*")
    @EntityAttributeAccess(entityClass = Students.class, view = "*")
    @EntityAttributeAccess(entityClass = Subject.class, view = "*")
    @EntityAttributeAccess(entityClass = JournalArticle.class, modify = "*")
    @EntityAttributeAccess(entityClass = Book.class, modify = "*")
    @EntityAttributeAccess(entityClass = Author.class, modify = "*")
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
