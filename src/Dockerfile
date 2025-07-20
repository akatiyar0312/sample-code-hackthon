# Use a lightweight Java runtime as base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your Spring Boot JAR (change name as needed)
COPY target/my-service.jar app.jar

# Expose port used in application.properties (default 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
