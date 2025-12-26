# Supply Chain BPEL on Apache ODE

![Java 11+](https://img.shields.io/badge/Java-11%2B-007396?logo=java&logoColor=white)
![Maven 3.6+](https://img.shields.io/badge/Maven-3.6%2B-C71A36?logo=apachemaven&logoColor=white)
![JavaFX 17.0.2](https://img.shields.io/badge/JavaFX-17.0.2-1B6AC6?logo=java&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-9%2B-F8DC75?logo=apachetomcat&logoColor=black)
![Apache ODE](https://img.shields.io/badge/Apache%20ODE-BPEL%20Engine-0B75AF?logo=apache&logoColor=white)

A BPEL orchestration project with a JavaFX client and Dockerized Apache ODE runtime.

## Project Structure

- `processes/` - BPEL process definitions.
- `clients/gui/` - JavaFX desktop application.
- `clients/java/` - Command-line SOAP client.
- `infra/docker/` - Docker configuration.

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

