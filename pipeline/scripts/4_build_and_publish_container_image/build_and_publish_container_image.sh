#!/bin/bash
################################################################################
#                       build_and_publish_container_image.sh                   #
#                                                                              #
# This script goal is to build the docker image of the exposition module       #
#                                                                              #
# Change History                                                               #
# 03/02/2021  Dan MAGIER           Script to build the container image of the  #
#                                  exposition module using JIB                 #
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

CONTAINER_BUILD_TYPE=$1
echo "${CONTAINER_BUILD_TYPE}"

CONTAINER_IMAGE=$2
echo "${CONTAINER_IMAGE}"

VERSION=$3
echo "${VERSION}"

################################################################################
# Build and publish the container image                                                                         #
################################################################################
function build_and_publish_container_image() {
  echo "Building container image"
  if [[ "${CONTAINER_BUILD_TYPE}" = "maven" ]]
  then
    ./pipeline/scripts/4_build_and_publish_container_image/build_docker_image_with_jib_and_maven.sh "${DOCKER_IMAGE}" "${VERSION}"
  elif [[ "${CONTAINER_BUILD_TYPE}" = "gradle" ]]
  then
    ./pipeline/scripts/4_build_and_publish_container_image/build_docker_image_with_jib_and_gradle.sh "${DOCKER_IMAGE}"
  elif [[ "${CONTAINER_BUILD_TYPE}" = "docker" ]]
  then
    ./pipeline/scripts/4_build_and_publish_container_image/build_docker_image_with_dockerfile.sh "${DOCKER_IMAGE}"
  else
      exit 1
  fi
}

###################################################
# Launch the build of the docker image of the
# exposition module depending of the options provided.
# Outputs:
#   A docker image of the exposition module
# Returns:
#   0 if everything went fine, else 1
####################################################
build_and_publish_container_image