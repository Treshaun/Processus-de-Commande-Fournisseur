# Java SOAP Client

This is a simple Java client to test the StoreProcess BPEL service.

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
