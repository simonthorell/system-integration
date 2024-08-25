# Use a Debian-based OpenJDK image as the base
FROM openjdk:24-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first (better cache usage)
COPY mvnw* pom.xml ./
COPY .mvn .mvn

# Download dependencies before copying the project files
RUN ./mvnw dependency:go-offline

# Copy the project files into the container
COPY . .

# Expose the port the application runs on
EXPOSE 8080

# Enable Spring Boot DevTools and mount the source code
CMD ["mvn", "spring-boot:run"]