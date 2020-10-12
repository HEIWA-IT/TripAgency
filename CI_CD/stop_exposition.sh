#!/bin/bash

MODE=$1

cd exposition

echo " Mode : " "${MODE}"

if [ "${MODE}" = "DOCKER" ]
then
        echo "Using Docker"
        cd docker && docker-compose stop && docker rm -v $(docker ps -q -f status=exited)
        cd ../..
else
        echo "Using CLI"

fi