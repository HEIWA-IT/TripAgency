#!/bin/bash

source ~/.env

APP_NAME=$1
VERSION=$2

DOCKER_LOCAL_IMAGE="${DOCKER_PROJECT_REGISTRY}/${APP_NAME}:${VERSION}"
echo ${DOCKER_LOCAL_IMAGE}
./mvnw versions:set -DnewVersion="${VERSION}"
xml ed -L -N p="http://maven.apache.org/POM/4.0.0" -u "//p:dependency[p:artifactId='domain']/p:version" -v "${VERSION}" pom.xml
xml ed -L -N p="http://maven.apache.org/POM/4.0.0" -u "//p:dependency[p:artifactId='infrastructure']/p:version" -v "${VERSION}" pom.xml
./mvnw ${MVN_SETTINGS} jib:build -pl exposition -Dimage=${DOCKER_LOCAL_IMAGE} -Djib.console=plain -Djib.httpTimeout=600000
./mvnw versions:revert