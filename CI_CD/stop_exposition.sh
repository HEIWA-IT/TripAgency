#!/bin/bash


cd exposition
# ../mvnw spring-boot:stop
cd docker && docker-compose stop
docker rm -v $(docker ps -q -f status=exited)
#../mvnw clean spring-boot:run
cd ..

