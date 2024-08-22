#!/bin/bash

# Step 1: Convert using Kompose
kompose convert -f docker-compose.yaml -o k8s/