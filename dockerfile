# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application's JAR file to the container
# Replace 'your-application.jar' with the name of your JAR file
COPY build/libs/AcctMgmtDockMS.jar app.jar

# Expose the port your application runs on
# Replace 8080 with your application's port if it's different
EXPOSE 8093

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
