#!/bin/bash
set -e
echo "Building and running ms-product, ms-brand, ms-notification..."
docker-compose build ms-product ms-brand ms-notification
docker-compose up -d ms-product ms-brand ms-notification
