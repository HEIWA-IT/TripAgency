#!/bin/bash
################################################################################
#                               build_with_maven.sh                                       #
#                                                                              #
# This script goal is to clean the project                                     #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to clean the project                 #
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

INITIAL_DIR=$(pwd)

################################################################################
# help                                                                         #
################################################################################
function help() {
  # Display Help
  echo "Display the options of this script."
  echo "Syntax: clean.sh [-gw|--gradlew|-g|--gradle|-g|--gradle|-m|--mvn|-h|--help]"
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
  ./gradlew clean || exit 1
}

################################################################################
# gradle                                                                       #
################################################################################
function gradle() {
  echo "Using Gradle"
  gradle clean || exit 1
}

################################################################################
# mvnw                                                                         #
################################################################################
function mvnw() {
  echo "Using mvnw"
  ./mvnw clean || exit 1
}

################################################################################
# mvn                                                                          #
################################################################################
function mvn() {
  echo "Using mvn"
  mvn clean || exit 1
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
################################################################################
###################################################
# Clean the entire project. The methods will depends of the chosen option
# Returns:
#   0 if everything went fine, else 1
####################################################

case $OPTIONS in
-h | --help) # display Help
  help
  exit
  ;;
-mw | --mvnw) # build with Maven wrapper
  mvnw ;;
-m | --mvn) # build with Maven
  mvn ;;
-gw | --gradlew) # build with Gradle wrapper
  gradlew ;;
-g | --gradle) # build with Gradle
  gradle ;;
*) # incorrect option
  echo "Error: Invalid option"
  exit
  ;;
esac