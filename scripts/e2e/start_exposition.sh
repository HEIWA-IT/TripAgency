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

function start_expostion() {
  cd exposition || exit

  echo "E2E_TEST_MODE : " "${E2E_TEST_MODE}"

  if [ "${E2E_TEST_MODE}" = "DOCKER" ]; then
    echo "Using Docker"
    cd docker && DOCKER_IMAGE="${DOCKER_IMAGE}" docker-compose up -d
    cd ../..
  else
    echo "Using the generated jar"
    MAVEN_REPOSITORY=$(../mvnw help:evaluate -Dexpression=settings.localRepository -q -DforceStdout)
    mkdir -p logs
    EXPOSITION_PATH="${MAVEN_REPOSITORY}"/com/heiwait/tripagency/exposition/"${VERSION}"/exposition-"${VERSION}".jar
    echo "exposition-jar path " "${EXPOSITION_PATH}"

    java -cp "${EXPOSITION_PATH}":./build/lib/* com.heiwait.tripagency.driver.exposition.ExpositionApplication >>./logs/log.txt &
    cd ..
  fi

  ./scripts/commons/wait-for-it.sh "http://localhost:12378/tripagency/swagger-ui/" -- echo "exposition is up"
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
start_expostion