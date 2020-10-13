#!/bin/bash

DOCKER_IMAGE=$1
VERSION=$2
MODE=$3

source ~/.env

E2E_TEST_MODE="${E2E_TEST_MODE}"

cd exposition || exit

echo " E2E_TEST_MODE : " "${E2E_TEST_MODE}"

if [ "${E2E_TEST_MODE}" = "DOCKER" ]
then
        echo "Using Docker"
        cd docker && DOCKER_IMAGE="${DOCKER_IMAGE}" docker-compose up -d
        cd ../..
else
        echo "Using the generated jar"
        MAVEN_REPOSITORY=$(../mvnw help:evaluate -Dexpression=settings.localRepository -q -DforceStdout)
        mkdir -p logs
        EXPOSITION_PATH="${MAVEN_REPOSITORY}"/com/heiwait/tripagency/exposition/"${VERSION}"/exposition-"${VERSION}".jar
        echo "exposition-jar path " "${EXPOSITION_PATH}"

        java -cp "${EXPOSITION_PATH}":./build/lib/* com.heiwait.tripagency.infrastructure.repository.driver.exposition.ExpositionApplication >> ./logs/log.txt &
        cd ..
fi

./CI_CD/wait-for-it.sh "http://localhost:12378/trip-agency/swagger-ui/" -- echo "exposition is up"
