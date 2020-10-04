#!/bin/bash

MODULE=$1
PROJECT_VERSION=$2
CUKEDOCTOR_MAIN_VERSION=3.2

MAVEN_REPOSITORY=$(./mvnw help:evaluate -Dexpression=settings.localRepository -q -DforceStdout)
CUKEDOCTOR_MAIN__JAR=${MAVEN_REPOSITORY}/com/github/cukedoctor/cukedoctor-main/${CUKEDOCTOR_MAIN_VERSION}/cukedoctor-main-${CUKEDOCTOR_MAIN_VERSION}.jar

cd $MODULE && java -jar $CUKEDOCTOR_MAIN__JAR \
    -o "build/TripAgency/TripAgency_living_documentation-${PROJECT_VERSION}" \
    -p "build/cucumber/TripAgency.json" \
		-t "TripAgency_living_documentation" \
		-f all \
		-numbered \
		-hideSummarySection \
		-hideScenarioKeyword