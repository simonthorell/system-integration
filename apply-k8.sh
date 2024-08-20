#!/bin/bash

# Navigate to the k8s kubernetes file directory
cd k8s

# Apply all YAML files
for file in *.yaml
do
  echo "Applying $file..."
  kubectl apply -f $file
done

echo "All files have been applied."