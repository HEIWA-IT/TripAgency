#!/bin/bash

########### Prérequis ###########
apt-get update;
apt-get install -y openssh-server;
mkdir -p ~/.ssh;
echo "$SSH_PRIVATE_KEY" >> ~/.ssh/docker-devtools.key;
chmod 600 ~/.ssh/docker-devtools.key;
echo "$SSH_CONFIG" >> ~/.ssh/config;
mkdir -p ~/.m2;
echo "$SETTINGS_XML" >> ~/.m2/settings.xml;
ssh -o StrictHostKeyChecking=no pxl uptime;
mkdir -p /opt/apps/conf;

########### Commandes utilisées dans les jobs ###########
#CMD_ARTIFACT_DOWNLOAD="mvn -Pnexus -U org.apache.maven.plugins:maven-dependency-plugin:3.0.1:copy -Dartifact=com.heiwa-it.tripagency:exposition:$VERSION:jar -DoutputDirectory=./";
CMD_DC_STOP="docker-compose stop";
CMD_DC_UP="docker-compose up -d";