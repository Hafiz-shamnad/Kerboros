import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create KDC components
        SecretKey tgsKey = CryptoUtil.generateKey();
        AuthenticationServer as = new AuthenticationServer(tgsKey);
        TicketGrantingServer tgs = new TicketGrantingServer(tgsKey);

        // Create the client
        Client client = new Client(as, tgs);

        // Client authenticates with the AS
        client.authenticate("user1", "password123");

        // Client requests a service ticket for "service1"
        ServiceTicket serviceTicket = client.requestServiceTicket("service1");

        // Create the service
        Service service = new Service("service1", tgs.serviceKeys.get("service1"));

        // Service validates the service ticket
        service.validateServiceTicket(serviceTicket);
    }
}
