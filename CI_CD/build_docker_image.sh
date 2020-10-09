#!/bin/bash

source ~/.env

DOCKER_IMAGE=$1
echo ${DOCKER_IMAGE}

./mvnw ${MVN_SETTINGS} jib:build -pl exposition -Dimage=${DOCKER_IMAGE} -Djib.console=plain -Djib.httpTimeout=600000
