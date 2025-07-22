# ✅ Use stable OpenJDK 17 image
FROM eclipse-temurin:17-jdk-jammy

# ✅ Create working directory inside container
WORKDIR /app

# ✅ Copy the built jar from target directory
COPY target/payments-0.0.1-SNAPSHOT.jar app.jar

# ✅ (Optional) Expose port for local clarity (Cloud Run doesn't rely on this)
EXPOSE 8080

# ✅ Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
