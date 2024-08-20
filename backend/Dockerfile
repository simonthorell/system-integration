# Use a Debian-based OpenJDK image as the base
FROM openjdk:18-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Create a basic pom.xml if it does not exist
RUN if [ ! -f pom.xml ]; then \
    echo '<?xml version="1.0" encoding="UTF-8"?>' > pom.xml && \
    echo '<project xmlns="http://maven.apache.org/POM/4.0.0"' >> pom.xml && \
    echo '         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"' >> pom.xml && \
    echo '         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">' >> pom.xml && \
    echo '    <modelVersion>4.0.0</modelVersion>' >> pom.xml && \
    echo '    <groupId>com.example</groupId>' >> pom.xml && \
    echo '    <artifactId>my-app</artifactId>' >> pom.xml && \
    echo '    <version>1.0-SNAPSHOT</version>' >> pom.xml && \
    echo '    <properties>' >> pom.xml && \
    echo '        <maven.compiler.source>1.8</maven.compiler.source>' >> pom.xml && \
    echo '        <maven.compiler.target>1.8</maven.compiler.target>' >> pom.xml && \
    echo '    </properties>' >> pom.xml && \
    echo '</project>' >> pom.xml; \
    fi

# Download and set up Maven Wrapper (if it's not already present)
RUN if [ ! -f mvnw ]; then \
      mvn -N io.takari:maven:wrapper && \
      chmod +x mvnw; \
    fi

# Run Maven to clean and package the application
RUN ./mvnw clean package

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/your-backend-app.jar"]