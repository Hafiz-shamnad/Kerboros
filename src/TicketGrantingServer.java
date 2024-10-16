import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TicketGrantingServer {
    final Map<String, SecretKey> serviceKeys;  // Service name to key map
    private final SecretKey tgsKey;  // TGS secret key

    public TicketGrantingServer(SecretKey tgsKey) throws Exception {
        this.tgsKey = tgsKey;
        this.serviceKeys = new HashMap<>();
        // Simulated services and their secret keys
        serviceKeys.put("service1", CryptoUtil.generateKey());
    }

    public ServiceTicket issueServiceTicket(String tgt, SecretKey sessionKey, String serviceName) throws Exception {
        // Validate the TGT by decrypting it using the TGS key
        String decryptedTGT = CryptoUtil.decrypt(tgsKey, tgt);
        if (!decryptedTGT.contains(Base64.getEncoder().encodeToString(sessionKey.getEncoded()))) {
            throw new SecurityException("Invalid TGT or session key");
        }

        // Generate a service session key
        SecretKey serviceSessionKey = CryptoUtil.generateKey();
        SecretKey serviceKey = serviceKeys.get(serviceName);
        if (serviceKey == null) throw new SecurityException("Unknown service");

        // Encrypt the service session key with the service key
        String encryptedServiceTicket = CryptoUtil.encrypt(serviceKey, "ServiceSessionKey: " +
                Base64.getEncoder().encodeToString(serviceSessionKey.getEncoded()));

        return new ServiceTicket(encryptedServiceTicket, serviceSessionKey);
    }
}
