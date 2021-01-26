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

APISERVER=${APISERVER}
TOKEN=${TOKEN}
SECRET_NAME=${SECRET_NAME}
CERTIFICATE_AUTHORITY_DATA=${CERTIFICATE_AUTHORITY_DATA}
CLUSTER=kubernetes
USER=default
NAMESPACE=gitlab-managed-apps
################################################################################


#######################################
# Connect to the Kubernetes cluster to execute commands via cli
# Returns:
#   0 if everything went fine, else 1.
#######################################
function connect_to_kubernetes_cluster()
{
  kubectl config set-cluster "${CLUSTER}" --server="${APISERVER}" --insecure-skip-tls-verify=true
  kubectl config set-context "${CLUSTER}"-context --cluster="${CLUSTER}"
  kubectl config set-credentials "${USER}" --token="${TOKEN}"
  kubectl config set-context "${CLUSTER}"-context --user="${USER}" --namespace="${NAMESPACE}"
  kubectl config use-context "${CLUSTER}"-context
}

function deploy()
{
  # sample value for your variables
  IMAGE_TO_DEPLOY="heiwait/trippricer-exposition:latest"

  # read the yml template from a file and substitute the string
  # {{MYVARNAME}} with the value of the IMAGE_TO_DEPLOY variable
  template=`cat "./pipeline/scripts/6_deployment/deployment.yaml.template" | sed "s/{{IMAGE}}/$IMAGE_TO_DEPLOY/g"`

  # apply the yml with the substituted value
  echo "$template" | kubectl apply -f -
}


function deploy_to_kubernetes()
{
  connect_to_kubernetes_cluster
  kubectl get pods
  deploy
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