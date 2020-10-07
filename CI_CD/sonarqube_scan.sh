#!/bin/bash
source ~/.env
sonar-scanner -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_CREDENTIALS} -Dsonar.sourceEncoding=UTF-8