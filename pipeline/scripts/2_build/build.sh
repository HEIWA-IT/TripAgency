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
# build                                                                      #
################################################################################
function build()
{
  echo "Using building artifacts"
  if [[ "${BUILD_TYPE}" = "maven" ]]
    then
      ./pipeline/scripts/2_build/build_with_maven.sh "${VERSION}"
    else
      ./pipeline/scripts/2_build/build_with_gradle.sh "${VERSION}"
  fi
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
################################################################################

###################################################
# Launch the build of the project depending of the
# options provided.
# Outputs:
#   Different artifacts stored in the build folder
# Returns:
#   0 if everything went fine, else 1
####################################################
build
