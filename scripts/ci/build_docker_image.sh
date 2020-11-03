#!/bin/bash
################################################################################
#                       build_docker_image.sh                                  #
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

VERSION=$2

MVN_JIB_COMMAND="compile ${MVN_SETTINGS} jib:build -pl exposition -Dusername=${DOCKER_REGISTRY_USERNAME} \
-Dpassword=${DOCKER_REGISTRY_PASSWORD} -Dimage=${DOCKER_IMAGE} -Djib.console=plain -Djib.httpTimeout=600000"

GRADLE_JIB_COMMAND="jib \
    -Djib.from.image=openjdk:11-jdk-slim \
    -Djib.from.auth.username=${DOCKER_REGISTRY_USERNAME} \
    -Djib.from.auth.password=${DOCKER_REGISTRY_PASSWORD} \
    -Djib.to.image=${DOCKER_IMAGE} \
    -Djib.to.auth.username=${DOCKER_REGISTRY_USERNAME} \
    -Djib.to.auth.password=${DOCKER_REGISTRY_PASSWORD} \
    -Djib.console=plain -Djib.httpTimeout=600000"

################################################################################
# help                                                                         #
################################################################################
function help() {
  # Display Help
  echo "Display the options of this script."
  echo "Syntax: build_docker_image.sh [-gw|--gradlew|-g|--gradle|-g|--gradle|-m|--mvn|-h|--help]"
  echo "options:"
  echo "-gw|--gradlew      Use Gradle wrapper to build the docker image."
  echo "-g|--gradle        Use Gradle to build the docker image."
  echo "-mw|--mvnw         Use Maven wrapper to revert the poms to its former state."
  echo "-m|--mvn           Use Maven  to revert the poms to its former state."
  echo "-h|--help          Print this Help."
  echo
}

################################################################################
# gradlew                                                                      #
################################################################################
function gradlew() {
  echo "Using gradlew"
  #./gradlew ${GRADLE_JIB_COMMAND} || exit 1
  ./mvnw versions:set -DnewVersion="${VERSION}" || exit 1
  mvnw
  ./mvnw versions:revert || exit 1

}

################################################################################
# gradle                                                                       #
################################################################################
function gradle() {
  echo "Using gradle"
  #gradle ${GRADLE_JIB_COMMAND} || exit 1
  ./mvnw versions:set -DnewVersion="${VERSION}" || exit 1
  mvnw
  ./mvnw versions:revert || exit 1
}

################################################################################
# mvnw                                                                         #
################################################################################
function mvnw() {
  echo "Using mvnw"
  echo ./mvnw ${MVN_JIB_COMMAND}
  ./mvnw ${MVN_JIB_COMMAND} || exit 1
}

################################################################################
# mvn                                                                          #
################################################################################
function mvn() {
  echo "Using mvn"
  echo mvn ${MVN_JIB_COMMAND}
  mvn ${MVN_JIB_COMMAND} || exit 1
}

###################################################
# Launch the build of the docker image of the
# exposition module depending of the options provided.
# Outputs:
#   A docker image of the exposition module
# Returns:
#   0 if everything went fine, else 1
####################################################

case $OPTIONS in
-h | --help) # display Help
  help
  exit
  ;;
-mw | --mvnw) # build with Maven wrapper
  mvnw
  exit
  ;;
-m | --mvn) # build with Maven
  mvn
  exit
  ;;
-gw | --gradlew) # build with Gradle wrapper
  gradlew
  exit
  ;;
-g | --gradle) # build with Gradle
  gradle
  exit
  ;;
*) # incorrect option
  echo "Error: Invalid option"
  exit
  ;;
esac
