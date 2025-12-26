# Supply Chain BPEL on Apache ODE

A BPEL orchestration project with a JavaFX client and Dockerized Apache ODE runtime.

## Quick Start

0. **Clone the repository:**

   ```bash
   git clone https://github.com/ranicharradi/TP4.Processus-de-Commande-Fournisseur
   cd Processus-de-Commande-Fournisseur
   ```

1. **Build the project:**

   ```bash
   mvn clean package
   ```

2. **Start the server (Apache ODE):**

   ```bash
   .\docker-up.bat
   ```

   To stop the server:

   ```bash
   .\docker-down.bat
   ```

3. **Run the Desktop Client:**
   ```bash
   mvn -pl clients/gui javafx:run
   ```

## Project Structure

- `processes/` - BPEL process definitions.
- `clients/gui/` - JavaFX desktop application.
- `clients/java/` - Command-line SOAP client.
- `infra/docker/` - Docker configuration.
