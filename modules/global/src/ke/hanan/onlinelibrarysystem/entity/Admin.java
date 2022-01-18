package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.security.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "onlinelibrarysystem_Admin")
public class Admin extends User {
    private static final long serialVersionUID = -2320498602453392180L;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "NATIONAL_ID")
    private String nationalId;

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
}