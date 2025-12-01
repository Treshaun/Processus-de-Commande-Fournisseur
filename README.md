# Supply Chain BPEL on Apache ODE

Minimal Apache ODE/Tomcat setup that hosts the three processes (`StoreProcess`, `ManufacturerProcess`, `ShipperProcess`) inside a single Docker container.

## Run

```bash
docker-compose build --no-cache
docker-compose up -d
```

Wait until the container logs show all three processes deployed, then send a SOAP request to the Store service (the only entry point).

## Test Call

```bash
Invoke-WebRequest `
    -Uri "http://localhost:8080/ode/processes/StoreService.StorePort" `
    -Method Post `
    -ContentType "text/xml" `
    -Headers @{ SOAPAction = "http://supplychain.org/store/startRestock" } `
    -InFile "soap-request.xml"
```

Successful execution returns `Order Completed Successfully. Goods Manufactured and Shipped.` from the Store process after both callbacks arrive.

## Layout

- `Dockerfile`, `docker-compose.yml` – container image and service definition.
- `StoreProcess/`, `ManufacturerProcess/`, `ShipperProcess/` – BPEL processes plus their WSDL/XSD assets.
- `soap-request.xml` – sample payload hitting the Store entry point (triggers entire choreography).
