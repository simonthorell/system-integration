# System Integration

1. Create monolit git repo
2. Create frontend and backend projects:

- Frontend: Quasar CLI - npm init quasar
- Backend: Spring Boot install file - https://start.spring.io/

3. Rename the respective folders to frontend/backend

## Create Kubernets Manifests from docker-compose file

1. Install Kubernetes kompose - `brew install kompose` and `brew install minikube`
2. Run command: `kompose convert`
3. Start minicube: `minikube start`
4. Run `apply-k8.sh` to add files to minicube.
