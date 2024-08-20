# System Integration

This project demonstrates how you use Kubernetes to orchestrate a simple web-app.

This project utlize TypeScript/Vue/Quasar for the frontend, Java Spring Boot with JDBC for the backend and MySQL for database - all running in separated containers/pods.

## Front- and Backend Setup

1. Create monolit git repo
2. Create frontend and backend projects:

- Frontend: Quasar CLI: `npm init quasar`
- Backend: Create Spring Boot install file with Maven - https://start.spring.io/

3. Rename the respective folders from the default names to frontend/backend

## Install MySQL

1. Install with homebrew: `brew install mysql`
2. Run: `brew services start mysql`
3. Secure/Set password: `mysql_secure_installation`
4. Verify: `mysql --version`

## Setup & Run Kubernetes Cluster

Below instructions only apply for macOS.

1. Install Kubernetes kompose: `brew install kompose`
2. Install Minicube: `brew install minikube`
3. Run command: `kompose convert`
4. Start Docker Application
5. Start Minicube: `minikube start`
6. Run `apply-k8.sh` to add files to minicube.
7. Open minikube dashboard: `minikube dashboard`
