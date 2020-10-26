#!/bin/bash
################################################################################
#                               build.sh                                       #
#                                                                              #
# This script goal is to build of the project                                  #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to build the project                 #
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

VERSION=$1
echo Version: ${VERSION}

################################################################################
# help                                                                         #
################################################################################
function help()
{
  # Display Help
  echo "Display the options of this script."
  echo
  echo "Syntax: build.sh [-gw|--gradlew|-g|--gradle|-g|--gradle|-m|--mvn|-h|--help]"
  echo "options:"
  echo "-gw|--gradlew      Use Gradle wrapper to build the project."
  echo "-g|--gradle        Use Gradle to build the project."
  echo "-mw|--mvnw          Use Maven wrapper to revert the poms to its former state."
  echo "-m|--mvn            Use Maven  to revert the poms to its former state."
  echo "-h|--help          Print this Help."
  echo
}

################################################################################
# gradlew                                                                      #
################################################################################
function gradlew()
{
  echo "Using gradlew"
  ./gradlew build || exit 1
}

################################################################################
# gradle                                                                       #
################################################################################
function gradle()
{
  echo "Using gradle"
  gradle build || exit 1
}

################################################################################
# mvnw                                                                         #
################################################################################
function mvnw()
{
  echo "Using mvnw"
  ./mvnw deploy -pl !e2e -Drevision="$VERSION" ${MVN_SETTINGS} || exit 1
}

################################################################################
# mvn                                                                          #
################################################################################
function mvn()
{
  echo "Using mvnw"
  mvn deploy -pl !e2e -Drevision="$VERSION" ${MVN_SETTINGS} || exit 1
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
################################################################################
################################################################################
# Process the input options. Add options as needed.                            #
################################################################################
# Get the options

case ${OPTIONS} in
  -h|--help) # display Help
    help
    exit;;
  -mw|--mvnw) # build with Maven wrapper
    mvnw
    exit;;
  -m|--mvn) # build with Maven
    mvn
    exit;;
  -gw|--gradlew) # build with Gradle wrapper
    gradlew
    exit;;
  -g|--gradle) # build with Gradle
    gradle
    exit;;
  \?) # incorrect option
    echo "Error: Invalid option"
    exit;;
esac