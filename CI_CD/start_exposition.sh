#!/bin/bash

DOCKER_IMAGE=$1

cd exposition
cd docker && DOCKER_IMAGE=${DOCKER_IMAGE} docker-compose up -d && cd ../.. && ./CI_CD/wait-for-it.sh "http://localhost:12378/trip-agency/swagger-ui/" -- echo "exposition is up"
#../mvnw clean spring-boot:run
