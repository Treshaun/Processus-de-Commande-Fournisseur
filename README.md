# Supply Chain BPEL on Apache ODE

Multi-module Maven repo containing the BPEL orchestration assets, a Java SOAP client, and Docker infrastructure to host everything on Apache ODE/Tomcat.

## Build everything

```bash
mvn clean package
```

Artifacts:

- `processes/target/processes.zip` – archive with every BPEL deployment plus `samples/soap-request.xml`.
- `clients/java/target/store-client-1.0.0-SNAPSHOT.jar` – Java 11 client (also runnable via `exec:java`).
- `clients/gui/target/store-gui-1.0.0-SNAPSHOT.jar` – JavaFX desktop client (launch via `javafx:run`).
- Running the build also executes `wsimport` (via `jaxws-maven-plugin`) to regenerate the Java bindings from `StoreProcess/Store.wsdl`.

## Run Apache ODE with Docker

```bash
docker compose -f infra/docker/docker-compose.yml build
docker compose -f infra/docker/docker-compose.yml up -d
```

The Docker image copies the process definitions from `processes/src/main/resources/processes/**`. Rebuild the image after changing any BPEL files.

## Invoke the Store process from Java

```bash
mvn -pl clients/java exec:java
```

The client posts the same payload as `processes/src/main/resources/samples/soap-request.xml` to `http://localhost:8080/ode/processes/StoreService.StorePort` and prints the SOAP response.

## Desktop client

```bash
mvn -pl clients/gui javafx:run
```

The JavaFX UI lets you send multiple restock requests, overriding the endpoint at runtime while reusing the generated SOAP stubs.

## Repository layout

- `pom.xml` – Maven aggregator.
- `processes/` – Maven module that holds BPEL assets under `src/main/resources/processes/**` plus samples.
- `clients/java/` – Java 11 SOAP client (`java.net.http`).
- `clients/gui/` – JavaFX desktop client that reuses the generated SOAP bindings.
- `infra/docker/` – Dockerfile and compose file for the Apache ODE runtime.
- `.vscode/`, `.idea/` – IDE settings (ignored by Git).
