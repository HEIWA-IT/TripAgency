#!/bin/bash

sonar-scanner -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_CREDS} -Dsonar.sourceEncoding=UTF-8