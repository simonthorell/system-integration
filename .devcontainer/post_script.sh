#!/bin/bash

# Start MySQL in detached mode
docker-compose up --build -d mysql
echo "MySQL Database is running!"

# Setup MySQL with default user and password in a separate subshell
docker exec db-mysql mysql -u root -p{$DB_ROOT_PASSWORD} -e "
CREATE USER '{$DB_USERNAME}'@'%' IDENTIFIED BY '{$DB_PASSWORD}';
GRANT ALL PRIVILEGES ON *.* TO '{$DB_USERNAME}'@'%';
FLUSH PRIVILEGES;
SHOW GRANTS FOR '{$DB_USERNAME}'@'%';
"
# Notify User that MySQL setup is completed
echo "MySQL setup is completed successfully!"

# Build the backend in detached mode
docker-compose build backend

# Build the frontend in detached mode
docker-compose build frontend

# Start backend & frontend
docker-compose up