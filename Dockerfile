# Use the official Maven image to create a build artifact.
# Use a multi-stage build to reduce the final image size.
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and the source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a smaller base image for the final artifact
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the build artifact from the build stage
COPY --from=build /app/target/NoteSharingApp-0.0.1-SNAPSHOT.jar /app/NoteSharingApp.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/NoteSharingApp.jar"]
