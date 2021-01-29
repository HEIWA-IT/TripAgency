#!/bin/bash
################################################################################
#                       check_pipeline_variables.sh                            #
#                                                                              #
# This script goal is to check the variable use in the pipeline.               #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to check the variable use in the     #
#                                  pipeline                                    #
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

MANDATORY_VARIABLES=( "BUILD_TYPE" "MAVEN_REPOSITORY" "SONARQUBE_URL" "SONARQUBE_CREDENTIALS" "DOCKER_REGISTRY_URL"
"DOCKER_PROJECT_REGISTRY" "DOCKER_REGISTRY_USERNAME" "DOCKER_REGISTRY_PASSWORD" "MAVEN_REPOSITORY" "CUKEDOCTOR_MAIN_JAR" )

NON_MANDATORY_VARIABLES=( "MAVEN_SETTINGS" "E2E_TEST_MODE" )

###################################################
# Check if a variable is filled or not
# Returns:
#   1if the variable is not filled
####################################################
function check_variable() {
    var_name=$1
    eval var_value=\$"$var_name"

    if [ -z "${var_value}" ]
    then
      echo "${var_name}" is empty!!! && return 1
    else
        echo "${var_name}" filled
    fi
}


#######################################
# Check the mandatory variables for the makefile to execute the rules
# Outputs:
#   Writes the number of variables that are not filled
# Returns:
#   0 if all the mandatory variables are set,
#   else the number of mandatory variables not set.
#######################################
function check_mandatory_variables() {
  count=0
  for var_name in "${MANDATORY_VARIABLES[@]}"; do
    check_variable "$var_name"
    count=$(($?+count))
  done

  echo There are "${count}" mandatory variable\(s\) not filled!
  exit ${count}
}

#######################################
# Check the non mandatory variables for the makefile to execute the rules
# Outputs:
#   Writes the number of variables that are not filled
#######################################
function check_non_mandatory_variables() {
  count=0
  for var_name in "${NON_MANDATORY_VARIABLES[@]}"; do
    check_variable "$var_name"
    count=$(($?+count))
  done

  echo There are "${count}" non mandatory variable\(s\) not filled!
}

#######################################
# Check all the variables for the makefile to execute the rules
# Outputs:
#   Writes the number of variables that are not filled
# Returns:
#   0 if all the mandatory variables are set,
#   else the number of mandatory variables not set.
#######################################
function check_variables() {
  check_non_mandatory_variables
  check_mandatory_variables
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
check_variables
