#!/bin/bash

source ~/.env

APP_NAME=$1
VERSION=$2

DOCKER_LOCAL_IMAGE="${DOCKER_PROJECT_REGISTRY}/${APP_NAME}:${VERSION}"
echo ${DOCKER_LOCAL_IMAGE}

./mvnw ${MVN_SETTINGS} jib:build -pl exposition -Dimage=${DOCKER_LOCAL_IMAGE} -Djib.console=plain -Djib.httpTimeout=600000
