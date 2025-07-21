# Use a lightweight Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your JAR
COPY target/payments-0.0.1-SNAPSHOT.jar app.jar

# Expose default port (optional, for local dev)
EXPOSE 8080

# Use shell form to enable environment variable expansion
CMD java -Dserver.port=8080 -jar app.jar
