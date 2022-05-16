package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "ONLINELIBRARYSYSTEM_WORK_GROUPS")
@Entity(name = "onlinelibrarysystem_WorkGroups")
@NamePattern("%s|name")
public class WorkGroups extends StandardEntity {
    private static final long serialVersionUID = -1890325657637063825L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinTable(name = "ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK",
            joinColumns = @JoinColumn(name = "WORK_GROUPS_ID"),
            inverseJoinColumns = @JoinColumn(name = "LIBRARIANS_ID"))
    @ManyToMany
    private List<Librarians> librarians;

    @JoinTable(name = "ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK",
            joinColumns = @JoinColumn(name = "WORK_GROUPS_ID"),
            inverseJoinColumns = @JoinColumn(name = "ADMIN_ID"))
    @ManyToMany
    private List<Admin> admins;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "workGroup")
    private List<WorkGroupRoles> workgroupRoles;

    public List<WorkGroupRoles> getWorkgroupRoles() {
        return workgroupRoles;
    }

    public void setWorkgroupRoles(List<WorkGroupRoles> workgroupRoles) {
        this.workgroupRoles = workgroupRoles;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Librarians> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(List<Librarians> librarians) {
        this.librarians = librarians;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}