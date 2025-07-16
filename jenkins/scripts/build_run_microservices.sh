#!/bin/bash
set -e
echo "Building and running ms-product, ms-brand, ms-notification..."
docker-compose build products-service brands-service
docker-compose up -d products-service brands-service
