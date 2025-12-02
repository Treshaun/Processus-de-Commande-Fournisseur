# JavaFX Desktop Client

A lightweight JavaFX application that invokes the Store BPEL process through the generated JAX-WS bindings.

## Run

```bash
mvn -pl clients/gui javafx:run
```

Adjust `javafx.platform` in `clients/gui/pom.xml` if you are building on Linux or macOS.
