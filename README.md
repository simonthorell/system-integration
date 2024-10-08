# System Integration

This project demonstrates how you use Kubernetes to orchestrate a simple web-app.

This project utlize TypeScript/Vue/Quasar for the frontend, Java Spring Boot with JDBC for the backend and MySQL for database - all running in separated containers/pods.

<!-- ## SQL Database ER-Diagram
<img src="resources/er_diagram.png" width="450" height="450" alt="ER-Diagram"> -->

## Front- and Backend Setup

1. Create monolit git repo
2. Create frontend and backend projects:

- Frontend: Quasar CLI: `npm init quasar`
- `cd frontend` and `npm install` (for dev dependencies)
- Backend: Create Spring Boot install file with Maven - https://start.spring.io/
- Add the following in /backend/pom.xml:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

3. Rename the respective folders from the default names to frontend/backend

## Install MySQL

1. Install with homebrew: `brew install mysql`
2. Run: `brew services start mysql`
3. Secure/Set password: `mysql_secure_installation`
4. Verify: `mysql --version`

### Grant Access to User

1. `docker exec -it db-mysql mysql -u root -p` and enter root pw.
2. Create New User:

- `CREATE USER 'myuser'@'%' IDENTIFIED BY 'mypassword';`
- `GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%';`
- `FLUSH PRIVILEGES;`
- `SHOW GRANTS FOR 'myuser'@'%';`
- Test User: `mysql -h 127.0.0.1 -P 3307 -u myuser -p`

## Test Spring Boot REST API

1. Start up all instances with `docker-compose up`
2. In the `src/main/resources/application.properties` file in backend we exponse port 5000 to the docker network by adding `server.port=5000`.
3. Open a docker interactive shell on frontend instance `docker exec -it frontend /bin/sh`
4. Use curl to test the API: `curl "http://backend:5000/customers/who-bought?productName=Blue%20Jeans&color=Blue&size=L&brand=DenimCo"`

## Setup & Run Kubernetes Cluster

Below instructions only apply for macOS.

1. Install Kubernetes kompose: `brew install kompose`
2. Install Minicube: `brew install minikube`
3. Run command: `kompose convert`
4. Start Docker Application
5. Start Minicube: `minikube start`
6. Run `apply-k8.sh` to add files to minicube.
7. Open minikube dashboard: `minikube dashboard`

### Minikube commands:

- Delete all pods: `kubectl delete all --all`
- Apply manifest: `kubectl apply -f k8s-manifest.yaml`
