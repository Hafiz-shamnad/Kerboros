import javax.crypto.SecretKey;

public class Client {
    private final AuthenticationServer as;
    private final TicketGrantingServer tgs;
    private TicketGrantingTicket tgt;
    private SecretKey sessionKey;

    public Client(AuthenticationServer as, TicketGrantingServer tgs) {
        this.as = as;
        this.tgs = tgs;
    }

    public void authenticate(String username, String password) throws Exception {
        tgt = as.authenticate(username, password);
        sessionKey = tgt.getSessionKey();
        System.out.println("Authenticated and received TGT.");
    }

    public ServiceTicket requestServiceTicket(String serviceName) throws Exception {
        if (tgt == null) throw new IllegalStateException("Client is not authenticated.");
        return tgs.issueServiceTicket(tgt.getEncryptedTGT(), sessionKey, serviceName);
    }
}
