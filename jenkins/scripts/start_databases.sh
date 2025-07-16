#!/bin/bash
set -e
echo "Starting databases, Zookeeper, Kafka, and Keycloak with realm import..."
docker-compose up -d keycloak
docker-compose up -d products-service
docker-compose up -d brands-service
