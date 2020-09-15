#!/bin/bash

PROJECT_VERSION=$1
CUKEDOCTOR_MAIN_VERSION=$2

MAVEN_REPOSITORY=$(../mvnw help:evaluate -Dexpression=settings.localRepository -q -DforceStdout)
CUKEDOCTOR_MAIN__JAR=${MAVEN_REPOSITORY}/com/github/cukedoctor/cukedoctor-main/${CUKEDOCTOR_MAIN_VERSION}/cukedoctor-main-${CUKEDOCTOR_MAIN_VERSION}.jar

java -jar $CUKEDOCTOR_MAIN__JAR \
    -o "build/TripAgency/TripAgency_living_documentation-${PROJECT_VERSION}" \
    -p "build/cucumber/TripAgency.json" \
		-t "TripAgency_living_documentation" \
		-f all \
		-numbered \
		-hideSummarySection \
		-hideScenarioKeyword