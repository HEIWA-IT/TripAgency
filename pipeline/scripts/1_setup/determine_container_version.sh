#!/bin/bash
################################################################################
#                       determine_container_version.sh                                      #
#                                                                              #
# This script goal is to set the version of the different artifacts.           #
# The results depends of the git branch name.                                  #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to determine if the artifact to      #
#                                  build is a snapshot or a release            #
#                                                                              #
#                                                                              #
#                                                                              #
################################################################################
################################################################################
################################################################################
#                                                                              #
#  Copyright (C) 2007, 2021 Dan MAGIER                                         #
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

current_branch_name=$1
VERSION=$2
###################################################
# determine if the artifact to build is a snapshot or a release type
# Outputs:
#   Returns the type of artifact: snapshot or release
# Returns:
#   1 if a problem occurred else print the valu of the VERSION
####################################################
function determine_container_version() {

  if [[ "${current_branch_name}" != "master" ]] && [[ "${current_branch_name}" != "release"* ]] ;
  then
    CONTAINER_VERSION="${VERSION}-snapshot"
    echo "${CONTAINER_VERSION}"
    return 0
  else
    CONTAINER_VERSION="${VERSION}"
    echo "${CONTAINER_VERSION}"
    return 0
  fi

  echo "We have a problem here!!!!"
  exit 1
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
determine_container_version