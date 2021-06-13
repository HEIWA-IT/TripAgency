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

BUILD_TYPE=$1

################################################################################
# mvnw                                                                         #
################################################################################
function clean() {
  echo "Cleaning project"
  if [[ "${BUILD_TYPE}" = "maven" ]]
  then
     echo "Using mvnw"
    ./mvnw clean || exit 1
    ./mvnw versions:revert || exit 1
  elif [[ "${BUILD_TYPE}" = "gradle" ]]
  then
    echo "Using gradlew"
    ./gradlew clean || exit 1
  else
      exit 1
  fi
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
clean