#!/bin/bash
################################################################################
#                            delete_deployment_from_kubernetes.sh              #
#                                                                              #
# This script goal is to build of the project                                  #
#                                                                              #
# Change History                                                               #
# 19/01/2021  Dan MAGIER           Script to delete the deployment from        #
#                                  kubernetes                                  #
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
KUBERNETES_API_SERVER=${KUBERNETES_API_SERVER}
KUBERNETES_TOKEN=${KUBERNETES_TOKEN}
KUBERNETES_SECRET_NAME=${KUBERNETES_SECRET_NAME}
KUBERNETES_CERTIFICATE_AUTHORITY_DATA=${KUBERNETES_CERTIFICATE_AUTHORITY_DATA}
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
  kubectl config set-cluster "${CLUSTER}" --server="${KUBERNETES_API_SERVER}" --insecure-skip-tls-verify=true
  kubectl config set-context "${CLUSTER}"-context --cluster="${CLUSTER}"
  kubectl config set-credentials "${USER}" --token="${KUBERNETES_TOKEN}"
  kubectl config set-context "${CLUSTER}"-context --user="${USER}" --namespace="${NAMESPACE}"
  kubectl config use-context "${CLUSTER}"-context
}

function delete_deployment_from_kubernetes()
{
  connect_to_kubernetes_cluster
  kubectl get pods
  helm uninstall mytrip
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
delete_deployment_from_kubernetes