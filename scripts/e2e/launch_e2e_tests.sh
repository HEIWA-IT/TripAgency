#!/bin/bash
################################################################################
#                            launch_e2e_tests.sh                               #
#                                                                              #
# This script goal is to launch the end to end tests                           #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER        Script to launch the end to end tests          #
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
help()
{
  # Display Help
  echo "Display the options of this script."
  echo "Syntax: launch_e2e_tests.sh [-gw|--gradlew|-g|--gradle|-g|--gradle|-m|--mvn|-h|--help]"
  echo "options:"
  echo "-gw|--gradlew       Use Gradle wrapper to build the project."
  echo "-g|--gradle         Use Gradle to build the project."
  echo "-mw|--mvnw          Use Maven wrapper to revert the poms to its former state."
  echo "-m|--mvn            Use Maven  to revert the poms to its former state."
  echo "-h|--help           Print this Help."
  echo
}

################################################################################
# gradlew                                                                      #
################################################################################
gradlew()
{
  echo "Using Gradlew"
  ./gradlew :e2e:test || exit 1
}

################################################################################
# gradle                                                                       #
################################################################################
gradle()
{
  echo "Using Gradle"
  gradle :e2e:test || exit 1
}

################################################################################
# mvnw                                                                         #
################################################################################
mvnw()
{
  echo "Using Mvnw"
  ./mvnw versions:set -DnewVersion="${VERSION}" || exit 1
  ./mvnw -pl e2e test || exit 1
  ./mvnw versions:revert || exit 1
}

################################################################################
# mvn                                                                          #
################################################################################
mvn()
{
  echo "Using Mvnw"
  mvn versions:set -DnewVersion="${VERSION}" || exit 1
  mvn -pl e2e test || exit 1
  mvn versions:revert || exit 1
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
################################################################################
###################################################
# Launch the e2e tests.
# The methods will depends of the chosen option
# Returns:
#   0 if everything went fine, else 1
###################################################

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
  *) # incorrect option
    echo "Error: Invalid option"
    exit;;
esac