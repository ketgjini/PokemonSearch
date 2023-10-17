# Stage 1: Build the application using Gradle
FROM gradle:7.6.1-jdk17-alpine AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon --stacktrace

# Stage 2: Create the final image
FROM eclipse-temurin:17
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/pokemon-search.jar
HEALTHCHECK --interval=30s --timeout=3s CMD [ "curl", "-f", "http://localhost:8080/actuator/health" ] || exit 1
ENTRYPOINT ["java", "-Dspring.profiles.active=$SPRING_PROFILE", "-jar", "app/pokemon-search-0.0.1-SNAPSHOT.jar"]