# Trip Agency sample

## WARNING FOR MAVEN USERS: THE 'TARGET' FOLDER IS REPLACED BY A FOLDER NAMED 'BUILD'
And this for two reasons:
- the first is that 'build' is more accurate than 'target'.
- the second is that it is the same as gradle.  
We can say that we killed two birds with one stone.

## Summary
- [0 Requirements](#0-requirements)
- [1 Sample goals](#1-sample-goals)
- [2 Wrappers and practices to build](#2-wrappers-and-practices-to-build)
  - [2.1 Maven wrapper](#2-1-maven-wrapper)
  - [2.2 Gradle wrapper](#2-2-gradle-wrapper)
  - [2.3 BOM for Bills of Materials](#2-3-bom-for-bills-of-materials)
- [3 xDD](#3-xdd)
  - [3.1 DDD](#3-1-ddd)
  - [3.2 BDD](#3-2-bdd)
  - [3.3 TDD](#3-3-tdd)
- [4 Living Documentation](#4-living-documentation)
- [5 Hexagonal architecture](#5-hexagonal-architecture)
- [6 DataBase Managing tool](#6-dataBase-managing-tool)
- [7 I18n](#7-i18n)
- [8 CI/CD best practices](#8-cicd-best-practices)
- [9 Let's make it work](#9-lets-make-it-work)

## 0 Requirements


## 1 Sample goals
This sample was made to provide a concrete example on some best practices of the market regarding a java application providing Rest API.  
Everything may be not perfect. This sample will always be on WIP.  
Be kind and don't hesitate to do some PR/MR ^^  
Everyone is welcome!  

This sample has many goals:
- to use best building tools practices: use of BOM (Bill Of Materials) and wrapper (MavenWrapper and GradleWrapper in this sample)
- to teach the basis of automated tests with Cucumber (third phase of Behaviour Driven Development)
- to generate a living documentation based on the tests
- to use the hexagonal architecture, an architectural pattern that allows dissociating the domain code from the technical 
code and ease also the tests implementation without infrastructure
- to use i18n (internationalization)
- to use best practices for CI/CD pipelines (shell scripts in the project to be used in the different CI servers)
- to use a database managing tools (liquibase here)


 

## 4 Living Documentation
Cukedoctor  
See in the following repository in the domain module: domain/build/TripAgency   
You should see a pdf file containing the results of the cucumber tests.

## 5 Hexagonal architecture
Multi modules: 
- domain (the most important module)
- infrastructure (infrastructure repository) and exposition (infrastructure driver) for the technical ones

## 6 DataBase Managing tool
Liquibase: Database version control and deployment

## 7 I18n
to fill

## 8 CI/CD best practices
For all the following parts of this chapter, a better possibility is to encapsulate your command in a shell script.
You can also use a Makefile. This is what I have done here.
Create also an alias that can be called from a project and do the work.
Example **build** or **make build** for a **./mvnw install** or **./gradlew build** command.

## 9 Let's make it work
Have a look at the **Makefile** file to see the actions you can do.

### Compilation
To make the project build, you can use the following commands depending on the tool you want to use:
**./mvnw install** or **./gradlew build**

### Generate the living documentation
Here is the command to generate the living documentation with Cukedoctor where
- CUKEDOCTOR_MAIN_JAR is the path to 'cukedoctor-main.jar'
- PROJECT_VERSION is the version number of the project

This command should be executed after the execution of Cucumber tests. You can find some in the **domain** and **e2e** modules.

``
java -jar ${CUKEDOCTOR_MAIN_JAR} 
      -o "build/TripAgency/TripAgency_living_documentation-${PROJECT_VERSION}" 
      -p "build/cucumber/TripAgency.json" 
  	  -t "TripAgency_living_documentation" 
  	  -f all 
  	  -numbered 
  	  -hideSummarySection 
  	  -hideScenarioKeyword
``

### Generate sonar reports
Here is the command to execute sonar reports generation where:
- SONAR_URL is the sonar server url
- SONAR_CREDENTIALS are the credentials to send the reports
This two variable have been added in an **.env** file in the home folder.

sonar-scanner has to be installed for this command to work.

``
source ~/.env
sonar-scanner -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_CREDENTIALS} -Dsonar.sourceEncoding=UTF-8
``

### Launching locally your rest api
you can start the rest exposition by executing the following command line in the exposition folder:  
**./mvnw clean spring-boot:run**  

The url to display the swagger page is the following:  
**http://localhost:12378/tripagency/api/swagger-ui/**

### Consulting the H2 DB
Connect to this url:
**http://localhost:12378/tripagency/api/backend/h2-console**
Fill the information regarding your configuration

### Building the docker image of the exposition
To build the docker image of the exposition module, we use Jib.
To be able to do so, don't forget to fill the following file with the good credentials: **~/.docker/config.json**

Here are the commands to build the image:
``
cd exposition
./mvnw compile jib:build
``

A docker-compose file is present in the following folder to launch the image: **exposition/docker**
USe this command to launch it: **docker-compose up -d**
The url to display the swagger page is the following:  
**http://localhost:12378/tripagency/api/swagger-ui/**
Warning with the port. Here it is **12378**

### e2e testing
In the e2e testing part we use the script [wait-for-it.sh](https://github.com/vishnubob/wait-for-it/blob/master/wait-for-it.sh).
This script allows to wait for the docker image to have finished running the embedded web server.
You also need to install **timeout** or **gtimeout** to make this script works.

On OS X execute the following commands:
```
brew install coreutils
sudo ln -s /usr/local/bin/gtimeout /usr/local/bin/timeout
```

### logs
The logs are stored in the following folder : /var/log/tripagency

## Use the Makefile to compile and test the project

### Requirements

#### Install the needed tools to 
- docker and docker-compose

#### Fill an .env file
You can also us the Makefile included at the root folder of the project.
To use it, you will need to have the following files:
- **~/.env** with this content:
```
############################### DEVELOPMENT COMMONS VARIABLE ################################
export DOCKER_PROJECT_REGISTRY={{DOCKER_PROJECT_REGISTRY}}
export DOCKER_REGISTRY_PASSWORD={{DOCKER_REGISTRY_PASSWORD}}
export DOCKER_REGISTRY_URL={{DOCKER_REGISTRY_URL}}
export DOCKER_REGISTRY_USERNAME={{DOCKER_REGISTRY_USERNAME}}

export KUBERNETES_API_SERVER={{KUBERNETES_API_SERVER}}
export KUBERNETES_CERTIFICATE_AUTHORITY_DATA={{KUBERNETES_CERTIFICATE_AUTHORITY_DATA}}
export KUBERNETES_SECRET_NAME={{KUBERNETES_SECRET_NAME}}
export KUBERNETES_TOKEN={{KUBERNETES_TOKEN}}

export MAVEN_REPOSITORY_PASSWORD={{MAVEN_REPOSITORY_PASSWORD}}
export MAVEN_REPOSITORY_RELEASES={{MAVEN_REPOSITORY_RELEASES}}
export MAVEN_REPOSITORY_SNAPSHOTS={{MAVEN_REPOSITORY_SNAPSHOTS}}
export MAVEN_REPOSITORY_URL={{MAVEN_REPOSITORY_URL}}
export MAVEN_REPOSITORY_USERNAME={{MAVEN_REPOSITORY_USERNAME}}

export SONARQUBE_CREDENTIALS={{SONARQUBE_CREDENTIALS}}
export SONARQUBE_URL={{SONARQUBE_URL}}
################################################################
```

- **./pipelines/.project_env** with this content:
```
############################### BUILD PROJECT DEVELOPMENT VARIABLES ###########################
export APP_NAME=trippricer
export E2E_TEST_MODE=DOCKER

export BUILD_TYPE=maven
export HOST={{SONARQUBE_CREDENTIALS}}
export LOG_PATH={{SONARQUBE_CREDENTIALS}}
##############################################################################################

############################### GENERIC DEVELOPMENT VARIABLES ################################
export VERSION=$(shell git describe --tags --always)
export DOCKER_IMAGE=$(shell echo "${DOCKER_PROJECT_REGISTRY}/${APP_NAME}-exposition:${VERSION}")

export MAVEN_REPOSITORY=$(shell echo "${HOME}/.m2/repository")
export MAVEN_SETTINGS_XML=pipeline/.m2/settings.xml
export MAVEN_SETTINGS=$(shell echo "-s ${MAVEN_SETTINGS_XML} -Dmaven_repository_username=${MAVEN_REPOSITORY_USERNAME} -Dmaven_repository_password=${MAVEN_REPOSITORY_PASSWORD} -Dmaven_repository_url=${MAVEN_REPOSITORY_URL} -Dsonarqube_url=${SONARQUBE_URL}")
export GRADLE_SETTINGS=--rerun-tasks

export CUKEDOCTOR_MAIN_VERSION=3.5.1
export CUKEDOCTOR_MAIN_JAR=$(shell echo "${MAVEN_REPOSITORY}/com/github/cukedoctor/cukedoctor-main/${CUKEDOCTOR_MAIN_VERSION}/cukedoctor-main-${CUKEDOCTOR_MAIN_VERSION}.jar")
##############################################################################################
```

The placeholders (between double brackets) need to be fill with the correct values.  
Some values have been set (for example CUKEDOCTOR_MAIN_VERSION).  
You can change the value if it does not fit your environment, or the way you want to build your app.  

#### Commands to execute
**make ci** will build the different components, and the docker image.  
**make e2e** will launch the test by downloading the docker image locally and launch the tests of the module e2e.
**make clean** clean your local repository.

You can launch the following command **make ci && make e2e**.  
Check the living documentation generated in the domain and e2e modules.  
After that you clean it by executing the **make clean** command.  