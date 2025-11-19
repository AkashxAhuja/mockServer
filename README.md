# Mock Server

Spring Boot mock server that can return predefined payloads from either an in-memory map or a PostgreSQL/Aurora database. Switch between response sources by adjusting the `mockserver.source` property.

## Getting started

### Requirements
- Java 21
- Gradle 8.5 (wrapper JAR is not checked in; run `gradle wrapper --gradle-version 8.5` once to regenerate it before using
  `./gradlew`)
- PostgreSQL-compatible database (Aurora PostgreSQL supported)

### Prepare the Gradle wrapper
Because binary files are excluded from version control, the `gradle/wrapper/gradle-wrapper.jar` file must be regenerated
locally once before running any Gradle tasks:
```bash
gradle wrapper --gradle-version 8.5
```

### Running locally
1. Set environment variables for your database (only required when `mockserver.source=DATABASE`):
   - `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`
2. Choose the response source:
   - In-memory: `mockserver.source=IN_MEMORY`
   - Database (default): `mockserver.source=DATABASE`
3. Start the application:
   ```bash
   ./gradlew bootRun
   ```

### Build and run with Docker
1. Build the container image (Gradle is downloaded inside the builder stage, so no wrapper files are required locally):
   ```bash
   docker build -t mock-server .
   ```
2. Run the container, providing any necessary datasource and configuration properties as environment variables:
   ```bash
   docker run --rm -p 8080:8080 \
     -e DB_HOST=host.docker.internal \
     -e DB_PORT=5432 \
     -e DB_NAME=mockserver \
     -e DB_USER=mockuser \
     -e DB_PASSWORD=mockpassword \
     -e MOCKSERVER_SOURCE=DATABASE \
     mock-server
   ```
   Set `MOCKSERVER_SOURCE=IN_MEMORY` if you prefer the hard-coded payloads without a database.

### Database bootstrap (optional)
If you want to seed the database with the default payloads, enable SQL initialization:
```properties
spring.sql.init.mode=always
```
The included `schema.sql` and `data.sql` create and populate the `mock_responses` table.

## Endpoint usage

All mock traffic now goes through a single controller endpoint:

- `GET /mock?service=/api/v1/inq_handshake`

Provide the original service path (for example `/api/v1/sub_verify_otp`) as the `service` query parameter to retrieve its payload. When `mockserver.source=DATABASE`, the controller reads the payload from the `mock_responses` table; when `mockserver.source=IN_MEMORY`, it falls back to the defaults defined in code.

## Configuration notes
- When `mockserver.source=IN_MEMORY`, payloads are defined in `InMemoryMockResponseProvider`.
- When `mockserver.source=DATABASE`, payloads are loaded from the `mock_responses` table. Each row stores the endpoint path and a JSON payload string.
- Logging is scoped under `com.example.mockserver`.
