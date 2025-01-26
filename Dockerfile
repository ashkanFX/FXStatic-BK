FROM openjdk:17-jdk-slim

# Create directory for your app inside the container
WORKDIR /app

# Copy the JAR file from your local machine to the container
COPY target/securtiy-0.0.1-SNAPSHOT.jar /app/myapp.jar

# Expose the port that Spring Boot listens on (default 8080)
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "myapp.jar"]
