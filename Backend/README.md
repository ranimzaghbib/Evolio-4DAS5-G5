# Evolio backend

The backend is a Maven multi-module project. Each Spring Boot service is independently executable and owns its MySQL database:

| Service | Default port | MySQL database |
| --- | ---: | --- |
| User | 8081 | `TP-projet-4DSI5` |
| Course | 8082 | `course_db` |
| Payment | 8083 | `payment_db` |
| Enrollment | 8084 | `enrollment_db` |
| Quiz | 8085 | `quiz_db` |
| Community | 8086 | `community_db` |
| Recommendation | 8087 | `recommendation_db` |

The database URL uses `createDatabaseIfNotExist=true`, so MySQL creates each schema on first connection when the configured MySQL account has permission. Services do not share tables or repositories.

## Requirements

- Java 17+
- Maven 3.9+
- MySQL running on `localhost:3306`

By default, each service uses the local MySQL `root` account with an empty password, matching the provided local configuration. Override the connection with `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`; override the port with `SERVER_PORT`.

## Build and run

From `Backend`:

```shell
mvn clean package
mvn -pl user-service spring-boot:run
```

Run another module by replacing `user-service` with `course-service`, `payment-service`, `enrollment-service`, `quiz-service`, `community-service`, or `recommendation-service`.

Every service exposes `GET /api/health`. Domain entities, repositories, and business endpoints are intentionally not scaffolded yet.
