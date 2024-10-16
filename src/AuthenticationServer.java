import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationServer {
    private final SecretKey tgsKey;  // TGS secret key
    private final Map<String, String> userDatabase; // Username to password map

    public AuthenticationServer(SecretKey tgsKey) {
        this.tgsKey = tgsKey;
        this.userDatabase = new HashMap<>();
        // Simulated users in the database
        userDatabase.put("user1", "password123");
    }

    public TicketGrantingTicket authenticate(String username, String password) throws Exception {
        // Authenticate user by checking password
        if (!userDatabase.containsKey(username) || !userDatabase.get(username).equals(password)) {
            throw new SecurityException("Invalid credentials");
        }

        // Create a session key for the client and TGS
        SecretKey sessionKey = CryptoUtil.generateKey();
        String tgtContent = "SessionKey: " + Base64.getEncoder().encodeToString(sessionKey.getEncoded());

        // Create the TGT, encrypt it with the TGS key
        String encryptedTGT = CryptoUtil.encrypt(tgsKey, tgtContent);

        // Return the TGT and the session key
        return new TicketGrantingTicket(encryptedTGT, sessionKey);
    }
}

class TicketGrantingTicket {
    private final String encryptedTGT;
    private final SecretKey sessionKey;

    public TicketGrantingTicket(String encryptedTGT, SecretKey sessionKey) {
        this.encryptedTGT = encryptedTGT;
        this.sessionKey = sessionKey;
    }

    public String getEncryptedTGT() {
        return encryptedTGT;
    }

    public SecretKey getSessionKey() {
        return sessionKey;
    }
}
