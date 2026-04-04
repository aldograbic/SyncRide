<p align="center">
  <img src="src/main/resources/static/img/favicon.ico" alt="SyncRide icon" width="96">
</p>

# SyncRide

SyncRide is a Spring Boot carpooling web app for offering rides, managing user accounts, and connecting drivers with passengers.

## Current Features

- User registration and login
- Email confirmation and account setup
- Account and profile management
- Vehicle management
- Ride offering flow
- Legal and static pages

## Tech Stack

- Spring Boot
- Java 21
- MySQL

## Local Setup

1. Create your local `src/main/resources/application.properties` file.
2. Add your database, mail, and Cloudinary credentials.
3. Create the database schema with [`src/CREATE.sql`](src/CREATE.sql).
4. Seed the cities table with [`src/INSERT.sql`](src/INSERT.sql).
5. Start the app:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Tests

Run the test suite with:

```bash
./mvnw test
```

The tests use an in-memory H2 database, so they do not require your local MySQL server.

## Status

This project is an older app that has been revived and updated to run on Spring Boot 3.5.x.
