#!/usr/bin/env bash

cd "$(dirname "$0")"

docker-compose down

docker volume rm pg-data

docker-compose up -d