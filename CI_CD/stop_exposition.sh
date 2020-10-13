#!/bin/bash

MODE=$1

APP_PORT=12378
cd exposition || exit

echo " Mode : " "${MODE}"

if [ "${MODE}" = "DOCKER" ]
then
        echo "Using Docker"
        cd docker && docker-compose stop && docker rm -v "$(docker ps -q -f status=exited)"
        cd ../..
else
        echo "Using CLI"
        kill "$(lsof -t -i:${APP_PORT})"
fi