# Use the latest OpenJDK image as the base
FROM openjdk:latest

# Set the working directory inside the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . .

# Compile the Java application (assuming you're using Maven)
RUN ./mvnw clean package

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/your-backend-app.jar"]
