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

MANDATORY_VARIABLE=true
NOT_MANDATORY_VARIABLE=false

#######################################
# Check if the variable as argument is filled
# Arguments:
#   - Variable name to check
#   - Is the variable mandatory
# Outputs:
#   Writes if the variable is filled or not
# Returns:
#   0 if the mandatory variable is filled or if it is a not mandatory variable,  else 1.
#######################################
function check_variable() {
  var_name=$1
  is_mandatory=$2

  eval var_value=\$"$var_name"
  ([ -z "${var_value}" ] && echo "${var_name}" is empty!!! && if ${is_mandatory}; then exit 1; fi || echo "${var_name}" filled)
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
  declare -i status=0

  check_variable "OPTIONS" $MANDATORY_VARIABLE
  status+=$?
  check_variable "MVN_SETTINGS" $NOT_MANDATORY_VARIABLE
  status+=$?
  check_variable "MAVEN_REPOSITORY" $MANDATORY_VARIABLE
  status+=$?

  check_variable "SONARQUBE_URL" $MANDATORY_VARIABLE
  status+=$?
  check_variable "SONARQUBE_CREDENTIALS" $MANDATORY_VARIABLE
  status+=$?

  check_variable "DOCKER_PROJECT_REGISTRY" $MANDATORY_VARIABLE
  status+=$?
  check_variable "DOCKER_REGISTRY_USERNAME" $MANDATORY_VARIABLE
  status+=$?
  check_variable "DOCKER_REGISTRY_PASSWORD" $MANDATORY_VARIABLE
  status+=$?

  check_variable "MAVEN_REPOSITORY" $MANDATORY_VARIABLE
  status+=$?
  check_variable "CUKEDOCTOR_MAIN_JAR" $MANDATORY_VARIABLE
  status+=$?

  check_variable "E2E_TEST_MODE" $NOT_MANDATORY_VARIABLE
  status+=$?

  echo There are ${status} mandatory variable\(s\) not filled!
  exit ${status}
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
check_variables