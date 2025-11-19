# syntax=docker/dockerfile:1

# Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /workspace/app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle --no-daemon bootJar

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
