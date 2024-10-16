import javax.crypto.SecretKey;

class ServiceTicket {
    private final String encryptedTicket;
    private final SecretKey serviceSessionKey;

    public ServiceTicket(String encryptedTicket, SecretKey serviceSessionKey) {
        this.encryptedTicket = encryptedTicket;
        this.serviceSessionKey = serviceSessionKey;
    }

    public String getEncryptedTicket() {
        return encryptedTicket;
    }

    public SecretKey getServiceSessionKey() {
        return serviceSessionKey;
    }
}