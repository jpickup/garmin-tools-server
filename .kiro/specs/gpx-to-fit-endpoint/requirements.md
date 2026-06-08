# Requirements Document

## Introduction

This feature adds a REST API endpoint to the Garmin Tools Spring Boot server that accepts a GPX file upload and returns a Garmin FIT course file. The conversion logic (`GpxToFit`) and its dependencies (`GpxReader`, `CourseConverter`) are already implemented and wired as Spring beans in `AppConfig`. The endpoint follows the same REST conventions, error handling, and response structure used by the existing `WorkoutScheduleController` endpoints.

> **Note:** A `RouteController` with a `POST /api/route/gpx` endpoint already exists in the codebase. These requirements formalise the expected behaviour of that endpoint and identify any gaps or improvements needed.

## Glossary

- **RouteController**: The Spring `@RestController` class at `com.johnpickup.garmintools.controller.RouteController` that handles GPX-related HTTP requests.
- **GpxToFit**: The conversion orchestrator class at `com.johnpickup.garmintools.convert.GpxToFit` that reads a GPX input stream and returns a FIT file as a byte array.
- **GPX_File**: A GPS Exchange Format (`.gpx`) XML file uploaded by the client as a multipart form-data request part.
- **FIT_File**: A Garmin Flexible and Interoperable Data Transfer (`.fit`) binary file returned to the client as an octet-stream download.
- **Client**: Any HTTP client (browser, curl, the Vue.js frontend) that calls the endpoint.
- **Server**: The Spring Boot application module (`server`).

---

## Requirements

### Requirement 1: Accept GPX File Upload

**User Story:** As a Client, I want to upload a GPX file via HTTP POST, so that I can trigger conversion to FIT format without needing local tooling.

#### Acceptance Criteria

1. THE RouteController SHALL expose a `POST /api/route/gpx` endpoint that accepts a `multipart/form-data` request containing a file part named `file`.
2. WHEN a request is received at `POST /api/route/gpx`, THE RouteController SHALL delegate conversion to the GpxToFit bean without performing any conversion logic itself.
3. WHEN the uploaded file has no filename or no file extension, THE RouteController SHALL return an HTTP 500 response with a descriptive error message.
4. WHEN the uploaded file has an extension other than `gpx` (case-insensitive), THE RouteController SHALL return an HTTP 500 response with a descriptive error message.

---

### Requirement 2: Return FIT File as Download

**User Story:** As a Client, I want to receive the converted FIT file as a downloadable binary response, so that I can save it directly to disk or forward it to a Garmin device.

#### Acceptance Criteria

1. WHEN conversion succeeds, THE RouteController SHALL return an HTTP 200 response with `Content-Type: application/octet-stream`.
2. WHEN conversion succeeds, THE RouteController SHALL set the `Content-Disposition` response header to `attachment; filename=<original_name>.fit`, where `<original_name>` is the uploaded filename with the `.gpx` extension replaced by `.fit`.
3. WHEN conversion succeeds, THE RouteController SHALL set the `Content-Length` response header to the exact byte length of the FIT file.
4. WHEN conversion succeeds, THE RouteController SHALL set the `Access-Control-Expose-Headers` and `Access-Control-Allow-Headers` response headers to `*` to allow the Client to read the `Content-Disposition` header.

---

### Requirement 3: GPX Parsing

**User Story:** As a Client, I want the Server to correctly parse any well-formed GPX file, so that valid routes are always converted without manual intervention.

#### Acceptance Criteria

1. WHEN a valid GPX file is provided, THE GpxToFit SHALL parse it into an internal `GpxType` representation using `GpxReader`.
2. WHEN an invalid or malformed GPX file is provided, THE GpxToFit SHALL propagate an exception to the RouteController.
3. IF GpxToFit propagates an exception, THEN THE RouteController SHALL catch it, log the error at ERROR level including the original filename, and throw a `RuntimeException` with a user-friendly message.
4. THE GpxReader SHALL produce a `GpxType` object that, when converted and serialised to FIT and then re-read, preserves the route name and the ordered sequence of track points (round-trip property).

---

### Requirement 4: GPX-to-FIT Conversion

**User Story:** As a Client, I want the converted FIT file to faithfully represent the original GPX route, so that the course can be loaded onto a Garmin device without data loss.

#### Acceptance Criteria

1. WHEN a GPX file containing a named track is provided, THE GpxToFit SHALL produce a FIT course whose name matches the track name from the GPX file.
2. WHEN a GPX file containing track points is provided, THE GpxToFit SHALL produce a FIT course containing the same number of course points as the GPX track points.
3. WHEN a GPX file is provided, THE GpxToFit SHALL write the FIT output to a temporary file, read the bytes, delete the temporary file, and return the bytes — leaving no temporary files on disk after a successful conversion.
4. IF the temporary file cannot be deleted after conversion, THEN THE GpxToFit SHALL log a warning and continue without throwing an exception.

---

### Requirement 5: CORS Support

**User Story:** As a Client running in a browser, I want the endpoint to support cross-origin requests, so that the Vue.js frontend can call the API from a different origin.

#### Acceptance Criteria

1. THE Server SHALL allow cross-origin POST requests to `POST /api/route/gpx` from any origin.
2. THE Server SHALL expose the `Content-Disposition` header to cross-origin clients so that the browser can read the suggested download filename.

---

### Requirement 6: Logging

**User Story:** As a developer, I want all significant steps of the conversion to be logged, so that I can diagnose failures in production.

#### Acceptance Criteria

1. WHEN a GPX upload request is received, THE RouteController SHALL log the original filename and file size at INFO level before processing begins.
2. WHEN conversion succeeds, THE RouteController SHALL log the output filename and byte count at INFO level.
3. WHEN an exception occurs during conversion, THE RouteController SHALL log the exception at ERROR level including the original filename.
4. WHEN GpxToFit converts a GPX file, THE GpxToFit SHALL log the course name and number of course points at INFO level after conversion.
