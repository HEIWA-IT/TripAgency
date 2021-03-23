#!/bin/bash
################################################################################
#                            start_exposition.sh                               #
#                                                                              #
# This script goal is to start the exposition for e2e testing                  #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to start the exposition for e2e      #
#                                  testing                                     #
#                                                                              #
#                                                                              #
################################################################################
################################################################################
################################################################################
#                                                                              #
#  Copyright (C) 2007, 2020 Dan MAGIER                                         #
#  dan@heiwa-it.com                                                            #
#                                                                              #
#  This program is free software; you can redistribute it and/or modify        #
#  it under the terms of the GNU General Public License as published by        #
#  the Free Software Foundation; either version 2 of the License, or           #
#  (at your option) any later version.                                         #
#                                                                              #
#  This program is distributed in the hope that it will be useful,             #
#  but WITHOUT ANY WARRANTY; without even the implied warranty of              #
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               #
#  GNU General Public License for more details.                                #
#                                                                              #
#  You should have received a copy of the GNU General Public License           #
#  along with this program; if not, write to the Free Software                 #
#  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA   #
#                                                                              #
################################################################################
################################################################################
################################################################################

DOCKER_IMAGE=$1
VERSION=$2

INITIAL_DIR=$(pwd)

function wait_for_exposition_to_start() {
  while [ $(curl -sw '%{http_code}' "${HOST}/tripagency/api/swagger-ui/" -o /dev/null) -ne 200 ]; do
    sleep 5;
  done
}

function use_docker() {
  echo "Using Docker"
  cd docker || exit
  DOCKER_IMAGE="${DOCKER_IMAGE}" docker-compose up -d
  cd "${INITIAL_DIR}" || exit
}

function use_generated_jar() {
  echo "Using the generated jar"
  mkdir -p logs
  EXPOSITION_PATH="${MAVEN_REPOSITORY}"/com/heiwait/tripagency/pricer/exposition/"${VERSION}"/exposition-"${VERSION}".jar
  echo "exposition-jar path " "${EXPOSITION_PATH}"
  java -cp "${EXPOSITION_PATH}":./build/lib/* com.bnpparibas.hackathon.yellowteam.yellowproject.driver.exposition.ExpositionApplication >>./logs/log.txt &
  cd "${INITIAL_DIR}" || exit
}

function start_exposition() {
  echo "E2E_TEST_MODE : " "${E2E_TEST_MODE}"

  cd exposition || exit
  if [[ "${E2E_TEST_MODE}" = "DOCKER" ]];
    then use_docker
    else use_generated_jar
  fi
  wait_for_exposition_to_start
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
start_exposition
