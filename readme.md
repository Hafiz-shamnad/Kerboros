# Kerberos Authentication System in Java

This project is a simplified implementation of the **Kerberos authentication protocol** in Java. The system simulates the interaction between the **client**, **Key Distribution Center (KDC)**, and **services** to provide secure, ticket-based authentication.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [File Structure](#file-structure)
- [Project Flow](#project-flow)
- [Contributing](#contributing)
- [License](#license)

## Project Overview
Kerberos is a secure, ticket-based authentication protocol used to authenticate users and services in a network. This project implements the core concepts of Kerberos, including **Ticket Granting Tickets (TGT)**, **Service Tickets**, and **AES encryption** for secure communication between entities.

### Core Components:
1. **Authentication Server (AS)**: Authenticates users and provides TGTs.
2. **Ticket Granting Server (TGS)**: Issues service tickets to authenticated users.
3. **Client**: Requests tickets from the AS and TGS.
4. **Service**: Verifies service tickets and grants access to the user.

## Features
- **AES Encryption**: Ensures that sensitive data such as session keys and tickets are securely encrypted.
- **Modular Design**: Each Kerberos component is implemented as a separate class, making it easy to integrate and modify.
- **Simplified Ticket Handling**: Tickets are issued, encrypted, and validated using symmetric cryptography.
- **Simulated Environment**: A simple flow that simulates the Kerberos process on a local machine.

## Technologies Used
- **Java 8 or higher**: Core programming language
- **AES Encryption**: Symmetric encryption for securing tickets and communication
- **Maven/Gradle** (Optional): For dependency management

## Setup and Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/KerberosProject.git
   cd KerberosProject
   ```

2. **Compile the Java files**:
   If using a terminal/command prompt, navigate to the `src/` directory and compile the files:
   ```bash
   javac -d ../bin *.java
   ```

3. **Run the program**:
   After compiling, run the main simulation from the `Main.java` class:
   ```bash
   java -cp ../bin Main
   ```

4. **(Optional)**: If you're using an IDE like IntelliJ IDEA or Eclipse, import the project and run it directly from the IDE.

## Usage

1. **Client Authentication**: The client authenticates with the Authentication Server (AS) using a username and password.
2. **Ticket Granting Ticket (TGT)**: Upon successful authentication, the client receives a TGT.
3. **Service Ticket Request**: The client presents the TGT to the Ticket Granting Server (TGS) and requests a service ticket for a particular service.
4. **Service Access**: The service verifies the ticket and grants the client access.

### Example Execution Flow
- Client authenticates with the AS using `user1` and `password123`.
- A **Ticket Granting Ticket (TGT)** is issued.
- The client requests a **service ticket** for `service1`.
- The service verifies the service ticket and grants access.

## File Structure

```plaintext
KerberosProject/
│
├── src/
│   ├── CryptoUtil.java           // Utility for AES encryption and decryption
│   ├── AuthenticationServer.java // Handles client authentication and TGT creation
│   ├── TicketGrantingServer.java // Issues service tickets after validating the TGT
│   ├── TicketGrantingTicket.java // Represents the TGT object
│   ├── ServiceTicket.java        // Represents the service ticket object
│   ├── Client.java               // Represents the client that interacts with AS and TGS
│   ├── Service.java              // The service that validates service tickets
│   └── Main.java                 // Main class to simulate the Kerberos flow
│
├── lib/                          // (Optional) Any third-party libraries you may use
│
└── README.md                     // Project description and instructions
```

## Project Flow

1. **Authentication Phase**: The client sends credentials to the Authentication Server (AS), which generates a TGT encrypted with the TGS secret key.
2. **Ticket Granting Phase**: The client uses the TGT to request a service ticket from the TGS.
3. **Service Access Phase**: The client sends the service ticket to the service, which decrypts and validates it using its secret key.

### Example Workflow
1. **Client** → AS: Sends username and password.
2. **AS** → Client: Returns TGT (encrypted).
3. **Client** → TGS: Sends TGT and service request.
4. **TGS** → Client: Returns service ticket.
5. **Client** → Service: Sends service ticket for access.
6. **Service**: Validates ticket and grants access.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any improvements or bugs you find.

### How to Contribute:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/my-new-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/my-new-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
