#!/bin/bash
################################################################################
#                       build_docker_image_with_jib_and_gradle.sh              #
#                                                                              #
# This script goal is to build the docker image of the exposition module       #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to build the docker image of the     #
#                                  exposition module using Maven and JIB       #
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
echo "${DOCKER_IMAGE}"

GRADLE_JIB_COMMAND="jib \
    -Djib.from.image=openjdk:11-jdk-slim \
    -Djib.from.auth.username=${DOCKER_REGISTRY_USERNAME} \
    -Djib.from.auth.password=${DOCKER_REGISTRY_PASSWORD} \
    -Djib.to.image=${DOCKER_IMAGE} \
    -Djib.to.auth.username=${DOCKER_REGISTRY_USERNAME} \
    -Djib.to.auth.password=${DOCKER_REGISTRY_PASSWORD} \
    -Djib.console=plain -Djib.httpTimeout=600000"

################################################################################
# gradlew                                                                      #
################################################################################
function build_docker_image_with_jib_and_gradle() {
  echo "Using gradlew"
  ./gradlew ${GRADLE_JIB_COMMAND} || exit 1
}

###################################################
# Launch the build of the docker image of the
# exposition module depending of the options provided.
# Outputs:
#   A docker image of the exposition module
# Returns:
#   0 if everything went fine, else 1
####################################################
build_docker_image_with_jib_and_gradle
