# Stage 1: Build
FROM gradle:8.7.0-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar

# Stage 2: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]