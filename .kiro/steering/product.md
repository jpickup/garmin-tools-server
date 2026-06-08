# Product Overview

**Garmin Tools** is a backend service that converts training workout schedules and GPS routes into formats compatible with Garmin devices.

## Core Functionality

- **Excel → FIT ZIP**: Reads training schedules from `.xls`/`.xlsx` files and converts them to Garmin FIT format, packaged as a ZIP archive ready for device upload.
- **Excel → iCalendar**: Converts the same Excel schedules to `.ics` calendar files for integration with calendar apps.
- **GPX → FIT**: Converts GPX route files to Garmin FIT course format.

## Users & Context

The service acts as the backend API for a Vue.js frontend (`garmintools-vue`). Users upload Excel training programmes and download the converted files. The Excel format follows a custom DSL parsed by an ANTLR grammar.

## Sample Data

Three example Excel workout schedules are in `data/` and can be used for manual testing.
