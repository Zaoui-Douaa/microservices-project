#!/bin/bash
set -e
echo "Building and running gateway-service..."
docker-compose build gateway-service
docker-compose up -d gateway-service
