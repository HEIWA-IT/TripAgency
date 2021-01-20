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

SERVER="https://95.111.239.40:6443"
CERTIFICATE_AUTHORITY_DATA="LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM1ekNDQWMrZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJeE1ERXdPREU0TlRRd04xb1hEVE14TURFd05qRTROVFF3TjFvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBSmFDCjZScEkxZFB1YlBxbWZXQUIrR1J2MHFSOVB5cDdJRHVDUzArZ2RvbnZOL3lCcHZBMmFyTHBuRjJZWWRqZ042ZEoKNmFGQ1c3QmF3U2xCRFZYTkNlRURLU3VEUFZIdjd2cSs2TWgxVVZUYnRQRSt2N3ZsRmgwTHpmNTE5eWxENEZ3LwpoVGdxY1k4bTVzaFl3S3FKeU52Uksvc2MzSkV3Yk91eE0wRHJCVVBKWlpKNkl1b1E2NWM3dklwOFB2SytMZzhBCitybWFPVUJqeTJxL0Zsc1VoMWQ3dWhkYzhpVWo5blcxenJFSUNLbEpQbGVIWTd2T3YySFZWcnhwMnJTWlVhMXcKd2FVTDMxaE85dnhEVjVKOXlDclVoeWI0WEhyVWdMVk0wWDB1L2xFKzBURDN5SDdQdXZaUTloNzMwVFowdXRJaQp5SWZodmorM2JIRUY2MU43TzhzQ0F3RUFBYU5DTUVBd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZGakNiMXc3RXAvdFF1alhXNFUrWXlNSlJXbmlNQTBHQ1NxR1NJYjMKRFFFQkN3VUFBNElCQVFBWFRVQUVnVm1paVZnb2tKR20yNmtIMWNYUStrYnc4Z2s1cFVHL1lvdURmNllXYU8vNwpMbmY5elBIVFliajJIMWZCNTBSYzViQml5eC9mazAxSjFRdVFIQ1RJV1lxNm9IeFNSMFRtTzlkQ2hsY2ppOCtCCjRRaVF3eml0MTVrNlhrL24xMm44MTFoZkFMcCs3TEVsWHpiWnQ3bmpjRVkzR0xTMGxUZjFrK1ltNUNNd1JhZSsKOTJEazZqckFXTDBIejdtaGZCT2IzSkNpb29RZ2VyTWpmcDdhcjFZN3RGQ25SZGdtWnRWSmhzc0VDbHNBT2VScApKMVVJbm0yNlh1TmpvR1ZESzQ4UUsvaUZ0dW5MckhLR2YvV3c3aVluM2s0L2NzRXRNaWw4ckNjWkM2Q2s3S0JDCnpXb0dyU2xiVzQyb3crVXVqYXJrR1dhWm1kNmpMeHdEcE5iMQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg=="
USER_TOKEN="eyJhbGciOiJSUzI1NiIsImtpZCI6ImJ3dnNXd1ZuNmRBb3o1R2N4VVhGaDZMcXRmYWNQMGRlSFV2MUdJdFdQeFkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJnaXRsYWItbWFuYWdlZC1hcHBzIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRlZmF1bHQtdG9rZW4tOHRqMmQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGVmYXVsdCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImQ0YTJiZWZjLTIxYmItNDAwNS04OTQ4LTM5OTQ2YzU2ZGRjOSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpnaXRsYWItbWFuYWdlZC1hcHBzOmRlZmF1bHQifQ.ZgQbc2ZWivfKY5gPQlidEmO0jaIx_WHxggpQo2KTnzloBv3SqHGkRowe3kf5-o4QC3n1qsCoATc5FaTGg_cYZO8JZTtSlAEk4FPTaku3VjhhyPw0O43I1sXPp-X_Q-pO3HlWfMJ_kCl7LH4Z9vqwmpPoijXjXad6nAh39ravVnvwGFzUObE_4Ub_ddm3eZKrhzT2CuYB3pOH65yD-4hOMHD2D3-nakAAw0vp3lXW10MNk0mA7OFvuBo5MWNgWx9yYeMltnjLTD7MfHglCS9nsPjepWx6h689rLjA8SmbaXo_4GijPYwofa1sHoDp3i36ZCd2aAdvtWPDihabzPoBgA"
DOCKER_IMAGE="heiwait/trippricer-exposition:9f450114"
APP_NAME=trippricer
################################################################################
function deploy_to_kubernetes()
{
#  sed -i "s/<DOCKER_IMAGE>/${DOCKER_IMAGE}/g" deployment.yaml
#  sed -i "s/<APP_NAME>/${APP_NAME}/g" deployment.yaml

  curl $SERVER/api --header "Authorization: Bearer $TOKEN" --insecure

 # kubectl config set-cluster kubernetes --server="${SERVER}"
  #kubectl config set clusters.k8s.certificate-authority-data ${CERTIFICATE_AUTHORITY_DATA}
  #kubectl config set-credentials gitlab-managed-apps --token="${USER_TOKEN}"
  #kubectl config set-context gitlab-managed-apps --cluster=kubernetes --user=default
  #kubectl config use-context gitlab-managed-apps
  #kubectl apply -f deployment.yaml
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