package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.security.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "onlinelibrarysystem_Librarian")
public class Librarian extends User {
    private static final long serialVersionUID = 8219611645602938143L;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "WORK_ID")
    private String workId;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}