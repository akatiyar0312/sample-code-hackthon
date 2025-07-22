FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/payments-0.0.1-SNAPSHOT.jar app.jar

# Do NOT hardcode a port; use the one Cloud Run provides
CMD java -Dserver.port=${PORT} -jar app.jar
