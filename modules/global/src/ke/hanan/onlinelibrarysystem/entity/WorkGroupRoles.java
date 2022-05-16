package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;

@PublishEntityChangedEvents
@Table(name = "ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES")
@Entity(name = "onlinelibrarysystem_WorkGroupRoles")
public class WorkGroupRoles extends StandardEntity {
    private static final long serialVersionUID = -5318845889209532423L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_GROUP_ID")
    private WorkGroups workGroup;

    @Column(name = "ROLE")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public WorkGroups getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(WorkGroups workGroup) {
        this.workGroup = workGroup;
    }
}