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
Maven and Gradle wrappers allows developers to use maven and gradle without installing it on their development machine.  
It is a script that invokes a declared version of one of this tool, and download it beforehand.  
Each solution allow to have a running project quickly and easily up to date with the last version!  

### 2-1 Maven wrapper
[Maven wrapper](https://github.com/takari/maven-wrapper)  

### 2-2 Gradle wrapper
[Gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)  

### 2-3 Bom for Bill Of Materials
[BOM](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#bill-of-materials-bom-poms) 
allows to define in a pom containing a dependency management element filled with dependecies and their versions.  
The BOM is used in others projects as a dependency management element.  
It allows to centralize the versions and even scope if you want (but this is not a good practice for the scope).  

All your projects can used the same dependencies by using the bom you have defined.  

Updating your dependencies is done by simply updating it in your bom.  
After that you just have to update the version of your bom in your projects.  

## 3 xDD
We try to follow a maximum the three following methods DDD - BDD - TDD to write the code in this sample.  

### 3-1 DDD
[Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design) (DDD) is the concept that the structure and 
language of software code (class names, class methods, class variables) should match the business domain.  
For example, if a software processes loan applications, it might have classes such as LoanApplication and Customer, and 
methods such as AcceptOffer and Withdraw.  

DDD connects the implementation to an evolving model.  

Domain-driven design is predicated on the following goals:

    placing the project's primary focus on the core domain and domain logic;
    basing complex designs on a model of the domain;
    initiating a creative collaboration between technical and domain experts to iteratively refine a conceptual model that addresses particular domain problems.

The term was coined by Eric Evans in his book of the same title.  

### 3-2 BDD
In software engineering, [Behavior Driven Development](https://en.wikipedia.org/wiki/Behavior-driven_development) (BDD) 
is an Agile software development process that encourages collaboration among developers, QA and non-technical or business 
participants in a software project.  
It encourages teams to use conversation and concrete examples to formalize a shared understanding of how the application should behave.  
It emerged from test-driven development (TDD).  
Behavior-driven development combines the general techniques and principles of TDD with ideas from domain-driven design 
and object-oriented analysis and design to provide software development and management teams with shared tools and a 
shared process to collaborate on software development.  

Although BDD is principally an idea about how software development should be managed by both business interests and 
technical insight, the practice of BDD does assume the use of specialized software tools to support the development process.  
Although these tools are often developed specifically for use in BDD projects, they can be seen as specialized forms of 
the tooling that supports test-driven development. The tools serve to add automation to the ubiquitous language that is 
a central theme of BDD.  

BDD is largely facilitated through the use of a simple domain-specific language (DSL) using natural-language constructs 
(e.g., English-like sentences) that can express the behaviour and the expected outcomes.  
Test scripts have long been a popular application of DSLs with varying degrees of sophistication.  
BDD is considered an effective technical practice especially when the "problem space" of the business problem to solve is complex.

BDD is now composed of three phases:
- Discovery : here Biz, Dev and QA exchanges about the domain. Dev and QA have question, Biz have the answers. 
Have a look at [example mapping](https://cucumber.io/blog/bdd/example-mapping-introduction/) workshop.
- Formulation : transforming the result of the discovery workshop into Gherkins syntax
- Automation : implementing the glue code to make executable the steps used in Cucumber written in Gherkins  

### 3-3 TDD
[Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development) (TDD) is a software development process 
that relies on the repetition of a very short development cycle: requirements are turned into very specific test cases, 
then the code is improved so that the tests pass.  
This is opposed to software development that allows code to be added that is not proven to meet requirements.  

American software engineer Kent Beck, who is credited with having developed or "rediscovered" the technique, stated in 
2003 that TDD encourages simple designs and inspires confidence.  

Test-driven development is related to the test-first programming concepts of extreme programming, begun in 1999, but 
more recently has created more general interest in its own right.  

Programmers also apply the concept to improving and debugging legacy code developed with older techniques.  

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
./mvnw compile jib:build
``

A docker-compose file is present in the following folder to launch the image: **exposition/docker**
USe this command to launch it: **docker-compose up -d**
The url to display the swagger page is the following:  
**http://localhost:12478/trip-agency/swagger-ui/**
Warning with the port. Here it is **12478**