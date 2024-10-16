import javax.crypto.SecretKey;

public class Service {
    private final String serviceName;
    private final SecretKey serviceKey;

    public Service(String serviceName, SecretKey serviceKey) {
        this.serviceName = serviceName;
        this.serviceKey = serviceKey;
    }

    public void validateServiceTicket(ServiceTicket ticket) throws Exception {
        // Decrypt the service ticket using the service key
        String decryptedTicket = CryptoUtil.decrypt(serviceKey, ticket.getEncryptedTicket());
        if (decryptedTicket.contains("ServiceSessionKey: ")) {
            System.out.println("Access granted to " + serviceName);
        } else {
            throw new SecurityException("Invalid service ticket");
        }
    }
}
