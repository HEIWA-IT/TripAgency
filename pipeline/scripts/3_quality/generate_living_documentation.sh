#!/bin/bash
################################################################################
#                      generate_living_documentation.sh                        #
#                                                                              #
# This script goal is to generate the living documentation of the project      #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to generate the living documentation #
#                                  of the project                              #
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

MODULE=$1
PROJECT_VERSION=$2
CUKEDOCTOR_MAIN_JAR=$3
###################################################
# Generate the project living documentation
# Outputs:
#   Different living documentation files inside a folder in the build folder
# Returns:
#   0 if everything went fine, else 1
####################################################
function generate_living_documentation() {
  cd "${MODULE}" && java -jar "${CUKEDOCTOR_MAIN_JAR}" \
    -o "build/YellowProject/yellowproject_living_documentation-""${PROJECT_VERSION}" \
    -p "build/cucumber/yellowproject.json" \
    -t "yellowproject_living_documentation" \
    -f all
    #\
#    -numbered \
#    -hideSummarySection \
#    -hideScenarioKeyword
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
generate_living_documentation