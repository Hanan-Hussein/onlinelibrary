package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

@PublishEntityChangedEvents
@Entity(name = "onlinelibrarysystem_Students")
public class Students extends User {
    private static final long serialVersionUID = -6436910326926123326L;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SCHOOL")
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @PrePersist
    public void prePersist(){
        active=true;
    }
}