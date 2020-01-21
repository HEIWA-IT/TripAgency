#!/bin/bash

echo "Launching build project and code project analysis with sonar";
mvn deploy sonar:sonar -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_CREDENTIALS $MAVEN_SONAR_OPTIONS;
