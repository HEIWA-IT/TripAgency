#!/bin/bash
################################################################################
#                       build_docker_image_with_jib.sh                                  #
#                                                                              #
# This script goal is to build the docker image of the exposition module       #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to build the docker image of the     #
#                                  exposition module depending on options      #
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
DOCKER_IMAGE=$1-SNAPSHOT
echo "${DOCKER_IMAGE}"

VERSION=$VERSION
echo "${VERSION}"
################################################################################
# gradlew                                                                      #
################################################################################
function build_docker_image() {
  echo "Building Docker image"
  if [[ "${BUILD_TYPE}" = "maven" ]]
  then
    ./pipeline/scripts/4_docker_build/build_docker_image_with_jib_and_maven.sh "${DOCKER_IMAGE}" "${VERSION}"
  elif [[ "${BUILD_TYPE}" = "gradle" ]]
  then
    ./pipeline/scripts/4_docker_build/build_docker_image_with_jib_and_gradle.sh "${DOCKER_IMAGE}"
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
build_docker_image
