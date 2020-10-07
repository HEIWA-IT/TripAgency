# Trip Agency sample

## WARNING FOR MAVEN USERS: THE 'TARGET' FOLDER IS REPLACED BY A FOLDER NAMED 'BUILD'
And this for two reasons:
- the first is that 'build' is more accurate than 'target'.
- the second is that it is the same as gradle.  
We can say that we killed two birds with one stone.

## Summary
- [1 Sample goals](#1-sample-goals)
- [2 Wrappers and practices to build](#2-wrappers-and-practices-to-build)
  - [2.1 Maven wrapper](#2-1-Maven-wrapper)
  - [2.2 Gradle wrapper](#2-2-Gradle-wrapper)
  - [2.3 BOM for Bills of Materials](#2-3-Bom-for-Bills-Of-Materials)
- [3 xDD](#3-xDD)
  - [3.1 DDD](#3-1-DDD)
  - [3.2 BDD](#3-2-BDD)
  - [3.3 TDD](#3-3-TDD)
- [4 Living Documentation](#4-Living-Documentation)
- [5 Hexagonal architecture](#5-Hexagonal-architecture)
- [6 DataBase Managing tool](#6-DataBase-Managing-tool)
- [7 I18n](#7-I18n)
- [8 CI/CD best practices](#8-CICD-best-practices)
- [9 Let's make it work](#9-Lets-make-it-work)

## 1 Sample goals
This sample was made to provide a concrete example on some best practices of the market regarding a java application providing Rest API.  
Everything may be not perfect. This sample will always be on WIP.  
Be kind and don't hesitate to do some PR/MR ^^  
Everyone is welcome!  

This sample has many goals:
- to use best building tools practices: use of BOM (Bills Of Materials) and wrapper (MavenWrapper and GradleWrapper in this sample)
- to teach the basis of automated tests based on Cucumber (Behaviour Driven Development)
- to generate a living documentation based on the tests
- to use the hexagonal architecture, an architectural pattern that allows dissociating the domain code from the technical 
code and ease also the tests implementation without infrastructure
- to use i18n (internationalization)
- to use best practices for CI/CD pipelines (shell scripts in the project to be used in the different CI servers)
- to use a database managing tools (liquibase here)

## 2 Wrappers and practices to build
to fill
### 2-1 Maven wrapper
to fill
### 2-2 Gradle wrapper
to fill
### 2-3 Bom for Bills Of Materials
to fill

## 3 xDD
to fill
### 3-1 DDD
Ubiquitous language
### 3-2 BDD
Specifications by examples implemented with Cucumber
### 3-3 TDD
to fill


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
**mvn clean spring-boot:run**  

The url to display the swagger page is the following:  
**http://localhost:12378/trip-agency/swagger-ui/**
Warning with the port. Here it is **12378**

### Consulting the H2 DB
Connect to this url:
**http://localhost:12378/trip-agency/h2-console**
Fill the information regarding your configuration

### Building the docker image of the exposition
To build the docker image of the exposition module, we use Jib.
To be able to do so, don't forget to fill the following file with the good credentials: **~/.docker/config.json**

Here are the commands to build the image:
``
cd exposition
mvn compile jib:build
``

A docker-compose file is present in the following folder to launch the image: **exposition/docker**
USe this command to launch it: **docker-compose up -d**
The url to display the swagger page is the following:  
**http://localhost:12478/trip-agency/swagger-ui/**
Warning with the port. Here it is **12478**