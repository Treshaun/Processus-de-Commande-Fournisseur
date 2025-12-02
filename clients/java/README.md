# Java SOAP Client

This is a simple Java client to test the StoreProcess BPEL service.

The Maven build runs `wsimport` (via `jaxws-maven-plugin`) against `processes/src/main/resources/processes/StoreProcess/Store.wsdl` so that all SOAP bindings are generated automatically under `target/generated-sources/wsimport`.

## Prerequisites

- Java 11 or higher
- Maven (optional, but recommended)

## How to Run

### Using Maven

From the repository root:

```bash
mvn -pl clients/java exec:java
```

Or run the command from this directory if you prefer:

```bash
mvn compile exec:java -Dexec.mainClass="com.client.StoreClient"
```

### Using pure Java

1. Compile:

   ```bash
   javac -d bin src/main/java/com/client/StoreClient.java
   ```

2. Run:

   ```bash
   java -cp bin com.client.StoreClient
   ```
