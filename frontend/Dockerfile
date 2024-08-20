# Use the official Node.js image as the base
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if available)
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Build the application for production
RUN npm run build

# Install a lightweight HTTP server for serving static files
RUN npm install -g http-server

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
CMD ["http-server", "dist"]
