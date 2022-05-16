package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.util.List;

@PublishEntityChangedEvents
@Entity(name = "onlinelibrarysystem_Librarians")
public class Librarians extends User {
    private static final long serialVersionUID = -3533952171253716423L;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @JoinTable(name = "ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK",
            joinColumns = @JoinColumn(name = "LIBRARIANS_ID"),
            inverseJoinColumns = @JoinColumn(name = "WORK_GROUPS_ID"))
    @ManyToMany
    private List<WorkGroups> workGroups;

    @Column(name = "NATIONAL_ID")
    private String nationalId;

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