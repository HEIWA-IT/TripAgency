#Trip-agency kata

# 1 - Kata goals
This kata has has two main goals :
- to teach the basis of BDD (Behaviour Driven Development)
- to use the hexagonal architecture, an architectural pattern allowing to dissociate domain code and technical code.

# 2 - Kata subject
You are in charge of a travel agency which sells trips all around the world.
The price of a trip is composed of the agency fees and the travel fees (hotels room, taxi, ticket, etc ...).

You'll have to develop an application responding to the previous need.

## First part: BDD
**The goal is to determine the price of a trip which is here _the sum of two numbers, the agency fees and the travel fees._**

You'll have to develop the core domain application following the next two rules:
- by doing the tests first, i.e. defining the behaviour, with the domain people and QA first.
- without relying on a real infrastructure by mocking the data provider (db, rest services, ...)

The second point can be done by mocking the intention represented by the java interfaces (for example in a java project).

## Second part: hexagonal architecture
Based on the project made in the first part, you'll now just have to implement the adapters based on the ports (the java interfaces
here) defined previously.  
You can exposed your service via a rest service.  
The repository adapter code can have values hard coded in a java class. In principle, it will be quickier to implement it like this.

# 3 - Hints to do the kata
The branch master as you can see contains only the README.md

You can use the following branches to help you doing some parts of this kata

## First part: BDD
### 01_template branch
It contains a maven project template.  
The dependencies and code already implemented allow you to work directly with cucumber.  
For the cucumber configuration look at the following class : BDDRunner
You should see or configure in which folders you can make the features files and the package in wich code the glue.

### 02_cucumber_features branch
01_template branch + the cucumber features files and the methods signatures in the java glue part.

### 03_BDD_solution branch
02_cucumber_features branch + one implementation of the domain and cucumber glue code implemented.

## Second part: hexagonal architecture
### 04_HA_adapter_to_develop branch
03_BDD_solution branch + the two technical modules
- the exposition module contains all that is required to do a rest call
- the infrastructure module contains only dependencies to the domain module. **You'll have to implement the adapters**

### 05_a_solution branch
04_HA_adapter_to_develop + one possibility for the adapter part

you can start the rest expostion by executing the following command line in the exposition folder:  
**mvn clean spring-boot:run**  

The url to use the service is the following:  
**http://localhost:12378/trip-agency/swagger-ui.html**

# 4 - Kata solution software architecture
## DDD
Ubiquitous language

## BDD
Specifications by examples implemented with Cucumber

## Living documentation
Cukedoctor  
See in the following repository in the domain module : target/TripFees   
You should see a pdf file containing the results of the cucumber tests.

## TDD
AssertJ + Mockito

## Hexagonal architecture
Multi modules: 
- domain (the most important module)
- infrastructure (infrastructure repository) and expostion (infrastructure driver) for the technical ones

## Liquibase
Database version control and deployment

## Rest exposition 
Swagger2
