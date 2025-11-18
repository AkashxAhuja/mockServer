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
   - In-memory (default): `mockserver.source=IN_MEMORY`
   - Database: `mockserver.source=DATABASE`
3. Start the application:
   ```bash
   ./gradlew bootRun
   ```

### Database bootstrap (optional)
If you want to seed the database with the default payloads, enable SQL initialization:
```properties
spring.sql.init.mode=always
```
The included `schema.sql` and `data.sql` create and populate the `mock_responses` table.

## Endpoints
- `GET /api/v1/inq_handshake`
- `GET /api/v1/sub_verify_otp`
- `GET /api/v1/sub_salary_details`
- `GET /api/v1/sub_eida_details`
- `GET /api/v1/sub_passport_details`
- `GET /api/v1/sub_address_details`
- `GET /api/v1/sub_employment_details`
- `GET /api/v1/sub_fatca_crs_details`
- `GET /api/v1/sub_delivery_pref`
- `GET /api/v1/sub_product_details`

## Configuration notes
- When `mockserver.source=IN_MEMORY`, payloads are defined in `InMemoryMockResponseProvider`.
- When `mockserver.source=DATABASE`, payloads are loaded from the `mock_responses` table. Each row stores the endpoint path and a JSON payload string.
- Logging is scoped under `com.example.mockserver`.
