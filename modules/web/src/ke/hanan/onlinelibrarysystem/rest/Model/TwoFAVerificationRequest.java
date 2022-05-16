package ke.hanan.onlinelibrarysystem.rest.Model;

public class TwoFAVerificationRequest {
    private String correlationId;
    private String otpCode;

    public TwoFAVerificationRequest() {
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}
