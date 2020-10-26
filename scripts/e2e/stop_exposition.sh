#!/bin/bash
################################################################################
#                            stop_exposition.sh                                #
#                                                                              #
# This script goal is to start the exposition for e2e testing                  #
#                                                                              #
# Change History                                                               #
# 01/10/2020  Dan MAGIER           Script to stop the exposition for e2e       #
#                                  testing                                     #
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

function stop_expostion() {
  APP_PORT=12378
  cd exposition || exit

  echo " E2E_TEST_MODE : " "${E2E_TEST_MODE}"

  if [ "${E2E_TEST_MODE}" = "DOCKER" ]; then
    echo "Using Docker"
    cd docker && docker-compose stop && docker rm -v "$(docker ps -q -f status=exited)"
    cd ../..
  else
    echo "Using CLI"
    kill "$(lsof -t -i:${APP_PORT})"
  fi
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
stop_expostion