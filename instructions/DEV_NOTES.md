# Internal Notes (Do Not Commit)

- Use Maven 3.9.11 + JDK 11+ for builds; `mvn clean package` from repo root builds all modules.
- Processes module outputs `processes/target/processes.zip` used for manual deployments.
- Docker assets live under `infra/docker`. When editing BPEL files rerun `docker compose -f infra/docker/docker-compose.yml build`.
- Java client runs via `mvn -pl clients/java exec:java`; keep sample payload synced with `processes/src/main/resources/samples/soap-request.xml`.
- Keep repo clean: no IDE folders (`.idea`, `.vscode`), no `out/`, no `.project`. Use `.gitignore` for local artifacts.
