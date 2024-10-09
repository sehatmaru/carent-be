# Stage 1: Build the application using OpenJDK 19 and Gradle
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Copy Gradle build files and source code
COPY ../settings.gradle.kts ./
COPY ../cust/build.gradle.kts cust/
COPY ../cust/gradlew cust/
COPY ../cust/gradle cust/gradle
COPY ../cust/src cust/src
COPY ../biz/build.gradle.kts biz/
COPY ../biz/src biz/src

WORKDIR /app/cust

# Set execution permission for the Gradle wrapper
RUN chmod +x ./gradlew

# Build the project and create the JAR
RUN ./gradlew clean build -x test

# Stage 2: Create the final Docker image using OpenJDK 19
FROM openjdk:21-jdk-slim
VOLUME /tmp

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/cust/build/libs/cust-1.0.0-SNAPSHOT.jar app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080