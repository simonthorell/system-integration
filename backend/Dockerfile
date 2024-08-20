# Use a Debian-based OpenJDK image as the base
FROM openjdk:24-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Run Maven to clean and package the application
RUN ./mvnw clean package

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/system-integration.jar"]