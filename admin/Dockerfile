# Stage 1: Build the application using OpenJDK 19 and Gradle
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Copy Gradle build files and source code
COPY ../settings.gradle.kts ./
COPY ../admin/build.gradle.kts admin/
COPY ../admin/gradlew admin/
COPY ../admin/gradle admin/gradle
COPY ../admin/src admin/src
COPY ../biz/build.gradle.kts biz/
COPY ../biz/src biz/src

WORKDIR /app/admin

# Set execution permission for the Gradle wrapper
RUN chmod +x ./gradlew

# Build the project and create the JAR
RUN ./gradlew clean build -x test

# Stage 2: Create the final Docker image using OpenJDK 19
FROM openjdk:21-jdk-slim
VOLUME /tmp

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/admin/build/libs/admin-1.0.0-SNAPSHOT.jar app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080