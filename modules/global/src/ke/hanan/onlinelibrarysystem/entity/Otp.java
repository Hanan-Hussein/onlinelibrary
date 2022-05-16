package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Table(name = "ONLINELIBRARYSYSTEM_OTP")
@Entity(name = "onlinelibrarysystem_Otp")
public class Otp extends StandardEntity {
    private static final long serialVersionUID = -6732346071856287539L;

    @Column(name = "OTP")
    private String otp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIBRARIAN_ID")
    private Librarians librarian;

    @Column(name = "REDEEMED")
    private Boolean redeemed;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "OTP_ATTEMPTS")
    private Integer otpAttempts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID")
    private Admin admin;

    public Librarians getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarians librarian) {
        this.librarian = librarian;
    }


    public void setRedeemed(Boolean redeemed) {
        this.redeemed = redeemed;
    }

    public Boolean getRedeemed() {
        return redeemed;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getOtpAttempts() {
        return otpAttempts;
    }

    public void setOtpAttempts(Integer otpAttempts) {
        this.otpAttempts = otpAttempts;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    @PrePersist
    public void prePersist() {
        //create token
        Random random = new Random();
        String token  = String.format("%04d", random.nextInt(10000));
        expiryDate = Date.from(createTs.toInstant().plus(1, ChronoUnit.MINUTES));
        redeemed = false;
        otpAttempts = 0;
        otp = token;
    }
}