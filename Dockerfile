# Stage 1: Build
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx512m -Dorg.gradle.daemon=false -Dkotlin.compiler.execution.strategy=in-process"
RUN gradle bootJar --no-daemon -x test

# Stage 2: Runtime
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Xmx512m", "-jar", "app.jar"]