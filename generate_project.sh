#!/bin/bash
################################################################################
#                               generate_project.sh                            #
#                                                                              #
# This script goal is to build of the project                                  #
#                                                                              #
# Change History                                                               #
# 10/11/2020  Dan MAGIER           Script to generate the project base         #
#                                  on a template                               #
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

#project_namespace={{project_namespace}}
#project_name={{project_name}}
#project_version={{project_version}}
#application_port={{application_port}}
#project_namespace_slashed={{project_namespace_slashed}}

project_namespace=com.paxleones
project_name=toto
project_version=revision
application_port=15031
project_namespace_slashed=com/paxleones

module_base_namespace=$project_namespace_slashed/$project_name

INITIAL_DIR=$(pwd)

function generate_folder_for()
{
  echo current folder: "$(pwd)"
  module_folder=$1
  module_part=$2
  echo Processing "$module_folder" "$module_part"

  cd "$module_folder"/"$module_part" || exit 1
  echo current folder: "$(pwd)"
  contents=($( ls -A ))
  echo Contents: "$contents"

  mkdir -p $module_base_namespace

  if [ "$( ls -A )" ]; then
    for content in $contents ; do
      echo "$content"
      mv "$content" $module_base_namespace/ || exit 1
    done
  else
    echo "$DIR is Empty"
  fi
  cd "$INITIAL_DIR" || exit 1
  echo
}

function generate_structure()
{
  generate_folder_for domain src/main/java && generate_folder_for domain src/test/java
  generate_folder_for repository src/main/java && generate_folder_for repository src/test/java
  generate_folder_for exposition src/main/java && generate_folder_for exposition src/test/java
  generate_folder_for e2e src/test/java
}

function replace_occurence()
{
  grep -rl $1 "$INITIAL_DIR" | xargs sed -i '' "s/$1/$2/g"
}

function replace_occurences()
{
  echo "$INITIAL_DIR"

  replace_occurence {{project_namespace}} ${project_namespace}
  replace_occurence {{project_name}} ${project_name}
  replace_occurence {{project_version}} ${project_version}
  replace_occurence {{application_port}} ${application_port}
}

function generate_project()
{
  generate_structure
  replace_occurences
}

generate_project