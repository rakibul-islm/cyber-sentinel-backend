# Build stage
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]