# Stage 1: Build
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx512m -Dorg.gradle.daemon=false"
RUN gradle bootJar --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]