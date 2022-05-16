package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.util.List;

@Listeners("onlinelibrarysystem_AdminChangedListener")
@PublishEntityChangedEvents
@Entity(name = "onlinelibrarysystem_Admin")
public class Admin extends User {
    private static final long serialVersionUID = -2320498602453392180L;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "NATIONAL_ID")
    private String nationalId;

    @JoinTable(name = "ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK",
            joinColumns = @JoinColumn(name = "ADMIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "WORK_GROUPS_ID"))
    @ManyToMany
    private List<WorkGroups> workGroups;

    public List<WorkGroups> getWorkGroups() {
        return workGroups;
    }

    public void setWorkGroups(List<WorkGroups> workGroups) {
        this.workGroups = workGroups;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @PrePersist
    public void prePersist() {
//        actionStatus=Status.UNAPPROVED.getId();
//        action=Action.CREATION.getId();
        active=true;

    }
}