# Tech Stack

## Language & Runtime
- **Java 23** (source and target)
- **Spring Boot 4.0.0-M3** (milestone release — be aware of potential API changes vs stable 3.x)

## Build System
- **Maven** (multi-module project, parent `pom.xml` at root)
- All modules inherit from the root POM; version is `0.0.1-SNAPSHOT`

## Key Libraries & Frameworks

| Library | Version | Purpose |
|---|---|---|
| Spring Boot | 4.0.0-M3 | REST API, DI, auto-configuration |
| Lombok | 1.18.42 | Boilerplate reduction (`@Getter`, `@Slf4j`, etc.) |
| Apache POI | 5.2.2 | Reading `.xls` / `.xlsx` Excel files |
| Garmin FIT SDK | 21.120.2 | Writing Garmin FIT format files |
| ANTLR | 4.7.1 | Parsing the workout schedule DSL |
| JAXB | 2.3.x | XML binding for GPX file parsing (XSD-generated classes) |
| Biweekly | 0.6.6 | iCalendar (`.ics`) generation |
| Commons IO | 2.14.0 | File/stream utilities |
| Logback | 1.5.13 | Logging implementation |
| JUnit | 4.13.1 | Unit testing |

## Common Commands

```bash
# Build all modules (skip tests)
mvn clean install -DskipTests

# Build and run all tests
mvn clean install

# Run the server locally (after build)
java -jar server/target/server-*.jar

# Build Docker image
docker build -t garmintools-server .

# Run full stack (backend on :8080, frontend on :8081)
docker-compose up
```

## API Endpoints

- `POST /api/schedule/fit` — Upload Excel file, receive FIT ZIP
- `POST /api/schedule/ics` — Upload Excel file, receive iCalendar file
- CORS is open to all origins for GET and POST

## Code Generation

- **ANTLR**: Generates parser/lexer Java classes from grammar files in `parser/` module
- **JAXB**: Generates GPX model classes from XSD schema in `gpx/` module
- **Lombok**: Annotation processor configured in `maven-compiler-plugin` for all modules
