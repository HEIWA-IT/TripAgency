#!/bin/bash

########### Prérequis ###########
apt-get update;
apt-get install -y openssh-server;

mkdir -p ~/.ssh;
echo "$SSH_PRIVATE_KEY" >> ~/.ssh/docker-devtools.key;
chmod 600 ~/.ssh/docker-devtools.key;
echo "$SSH_CONFIG" >> ~/.ssh/config;

ssh -o StrictHostKeyChecking=no pxl uptime;
mkdir -p /opt/apps/conf;

apt-get install -y jq;

##################### Commands used #####################
DOCKER_COMPOSE_STOP="docker-compose stop";
CLEAN_ARTIFACT_FOLDER="cd ./apps/artifacts/ && rm -f *.jar";
DOCKER_COMPOSE_UP="docker-compose up -d";
#########################################################

echo "downloadArtifact method";
URL=https://nexus.paxleones.com;
RESOURCES=service/rest/v1/search/assets;
REPOSITORY=paxleones;
GROUP=com.heiwait;
NAME=exposition;
BASE_VERSION=0.0.1-SNAPSHOT;
EXTENSION=jar;

ARTIFACT_URL=$(curl -u "$NEXUS_LOGIN":"$NEXUS_PASSWORD" -X GET "$URL/$RESOURCES?sort=version&direction=desc&repository=$REPOSITORY&group=$GROUP&name=$NAME&maven.baseVersion=$BASE_VERSION&maven.extension=$EXTENSION" -H  "accept: application/json" | jq '.items[0].downloadUrl');
echo "url to dowload artifact: " $ARTIFACT_URL;
#DOWNLOAD_ARTIFACT=$(curl -u dev:dev $GET_ARTIFACT_URL -fsSL -o exposition.jar);

########## Strating deployment ##########################
echo "Deploying artifact to $1";
ENV_FOLDER=/home/upxl/containers/tripagency/$1;
ssh pxl "cd $ENV_FOLDER && $DOCKER_COMPOSE_STOP && $CLEAN_ARTIFACT_FOLDER && curl -u $NEXUS_LOGIN:$NEXUS_PASSWORD $ARTIFACT_URL -fsSL -o exposition.jar && $DOCKER_COMPOSE_UP";
