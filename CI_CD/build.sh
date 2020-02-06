#!/bin/bash

mkdir -p ~/.m2;
echo "$SETTINGS_XML" >> ~/.m2/settings.xml;

echo "Launching build project and code project analysis with sonar";
mvn deploy;
mvn sonar:sonar -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_CREDENTIALS $MAVEN_SONAR_OPTIONS;
