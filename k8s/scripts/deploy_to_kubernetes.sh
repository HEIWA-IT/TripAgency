#!/bin/bash
################################################################################
#                               deploy_to_kubernetes.sh                        #
#                                                                              #
# This script goal is to build of the project                                  #
#                                                                              #
# Change History                                                               #
# 19/01/2021  Dan MAGIER           Script to deploy the project container      #
#                                  image to kubernetes                         #
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

VERSION=$1
APP_NAME=${APP_NAME}

HELM_CHART_FOLDER=./k8s/helm_chart/java-rest-api
HELM_VALUES_FILE=./k8s/helm_chart/java-rest-api/values.yaml

KUBERNETES_CONNECTION_TO_CLUSTER_SCRIPT=./k8s/scripts/connecting_to_kubernetes_cluster.sh

URI=tripagency/api/swagger-ui/
################################################################################

function deploy()
{
  helm upgrade --install -f "${HELM_VALUES_FILE}" "${APP_NAME}" "${HELM_CHART_FOLDER}" --set image.tag="${VERSION}"
  kubectl rollout status deployment "${APP_NAME}"-java-rest-api-"${VERSION}"

  while [ $(curl -sw '%{http_code}' "${HOST}/${URI}" -o /dev/null) -ne 200 ]; do
    sleep 5;
  done
}


function deploy_to_kubernetes()
{
  pwd
  "${KUBERNETES_CONNECTION_TO_CLUSTER_SCRIPT}"
  kubectl get pods
  deploy
  kubectl get pods
}

################################################################################
################################################################################
# Main program                                                                 #
################################################################################
################################################################################

###################################################
# Launch the deployment on the kubernetes cluster
# Returns:
#   0 if everything went fine, else 1
####################################################
deploy_to_kubernetes