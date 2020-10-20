#!/bin/bash

APP_PORT=12378
cd exposition || exit

echo " E2E_TEST_MODE : " "${E2E_TEST_MODE}"

if [ "${E2E_TEST_MODE}" = "DOCKER" ]
then
        echo "Using Docker"
        cd docker && docker-compose stop && docker rm -v "$(docker ps -q -f status=exited)"
        cd ../..
else
        echo "Using CLI"
        kill "$(lsof -t -i:${APP_PORT})"
fi