# Project Structure

## Module Layout

The project is a Maven multi-module build. Each module is a top-level directory.

```
garmintools/
├── common/      # Shared domain value objects (units, measurements)
├── parser/      # ANTLR grammar and generated parser for workout DSL
├── gpx/         # GPX file reading (JAXB-generated from XSD)
├── workout/     # Workout data models; Excel schedule reader
├── garmin/      # Garmin FIT format conversion logic
├── calendar/    # Schedule generation and calendar/Excel export
├── server/      # Spring Boot application — REST controllers, config
└── data/        # Sample Excel workout schedules (not compiled)
```

The `server` module is the only one that produces an executable JAR. All other modules are libraries consumed by `server`.

## Package Conventions

- Root package: `com.johnpickup`
- Module-specific roots:
  - `com.johnpickup.garmin.common.unit` — value objects (Distance, Pace, Power, HeartRate, Time)
  - `com.johnpickup.garmin.converter` — FIT format converters
  - `com.johnpickup.garmin.fit` — FIT SDK wrappers
  - `com.johnpickup.calendar` — schedule generation and writers
  - `com.johnpickup.workout` — workout models and Excel reader
  - `com.johnpickup.gpx` — GPX reader
  - `com.johnpickup.garmintools` — Spring Boot app (server module)
    - `.controller` — REST controllers
    - `.config` — Spring `@Configuration` classes
    - `.convert` — top-level conversion orchestrators

## Architectural Patterns

- **Value Objects**: Domain units (`Distance`, `Pace`, `Power`, `HeartRate`, `Time`) are immutable with final fields, custom `equals`/`hashCode`, and unit-conversion methods (e.g., `toGarminDistance()`).
- **Converter Pattern**: Each format transformation has a dedicated converter class. Factory classes (`HeartRateConverterFactory`, `PaceConverterFactory`, `PowerConverterFactory`) select the right converter at runtime.
- **Strategy Pattern**: `ExcelConverter` interface is implemented by `ExcelToFitZip` and `ExcelToIcal`, allowing the controller to delegate without knowing the output format.
- **Spring Configuration**: Beans are wired manually in `AppConfig` rather than relying on component scanning for non-Spring library classes.
- **Stateless Services**: All service/converter classes are stateless; state lives in the domain objects passed through them.

## Code Style

- Use Lombok annotations to reduce boilerplate: `@Getter`, `@Slf4j`, `@Builder`, etc.
- Prefer immutable objects with `final` fields for domain models.
- Use Java switch expressions (not switch statements) for type-based dispatch.
- Log via `@Slf4j` — never use `System.out`.
- Wrap checked exceptions in `RuntimeException` at the controller boundary with a user-friendly message.
- Keep controllers thin: delegate all logic to injected service/converter beans.
- The `common` module uses JPMS (`module-info.java`) — keep module boundaries clean.
