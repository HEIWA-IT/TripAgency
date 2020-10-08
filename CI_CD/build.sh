#!/bin/bash
################################################################################
#                                   build.sh                                   #
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

VERSION=$2
echo Version: "${VERSION}"
################################################################################
# Help                                                                         #
################################################################################
Help()
{
   # Display Help
   echo "Display the options of this script."
   echo
   echo "Syntax: build.sh [-g|m|h|v]"
   echo "options:"
   echo "g     Use Gradle wrapper to build the project."
   echo "m     Use Maven wrapper to build the project."
   echo "h     Print this Help."
   echo "v     Verbose mode."
   echo
}

################################################################################
# Gradlew                                                                         #
################################################################################
Gradlew()
{
   # Using Gradlew
   echo "Using Gradlew"
   echo
   ./gradlew build
   echo
}

################################################################################
# Mvnw                                                                         #
################################################################################
Mvnw()
{
   # Using Mvnw
   echo "Using Mvnw"
   echo
   ./mvnw install -Drevision="$1" -DskipTests
   echo
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
while getopts ":hgm" option; do
   case $option in
      h) # display Help
         Help
         exit;;
      g) # build with Gradle wrapper
         Gradlew
         exit;;
      m) # build with Maven wrapper
         shift
         Mvnw $1
         exit;;
     \?) # incorrect option
         echo "Error: Invalid option"
         exit;;
   esac
done
