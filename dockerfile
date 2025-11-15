# 1. Java 17 base image
FROM eclipse-temurin:17-jdk

# 2. Working directory
WORKDIR /app

# 3. Copy jar from Gradle build folder
COPY build/libs/rs-hardware-glass-and-electrical-2.0.0.jar app.jar

# 4. Expose Spring Boot port
EXPOSE 8080

# 5. Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
