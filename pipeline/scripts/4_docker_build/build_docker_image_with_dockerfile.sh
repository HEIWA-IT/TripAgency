#!/bin/bash
################################################################################
#                       build_docker_image_with_dockerfile.sh                                  #
#                                                                              #
# This script goal is to build the docker image of the exposition module       #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to build the docker image of the     #
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

DOCKER_IMAGE=$1
echo "${DOCKER_IMAGE}"

################################################################################
# docker                                                                         #
################################################################################
function build_docker_image_with_a_dockerfile() {
  echo "Using docker"

  cd exposition && docker build --tag "${DOCKER_IMAGE}" . || exit 1
  docker login -u=${DOCKER_PROJECT_REGISTRY} -p=${DOCKER_REGISTRY_PASSWORD}
  docker push "${DOCKER_IMAGE}"
}

###################################################
# Launch the build of the docker image of the
# exposition module depending of the options provided.
# Outputs:
#   A docker image of the exposition module
# Returns:
#   0 if everything went fine, else 1
####################################################
build_docker_image_with_a_dockerfile