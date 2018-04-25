#MyTrip kata

# 1 - Kata goals

This kata has has two main goals :
- to teach the basis of BDD (Behaviour Driven Development)
- to use the hexagoanl architecture, an architectural pattern allowing to dissociate domain code and technical code.

# 2 - Kata subject

You are in charge of a travel agency which sells trips all around the world.
The price of a trip is composed of the agency fees and the travel fees (hotels room, taxi, ticket, etc ...).

## First part: BDD
**The goal is to determine the price of a trip which is here _the sum of two numbers, the agency fees and the travel fees._**

You'll have to develop the core domain application following the next two rules:
- by doing the tests first, i.e. defining the behaviour, with the domain people and QA first.
- without relying on a real infrastructure by mocking the data provider (db, resst services, ...)

The second point can be done by mocking the intention represented by the java interfaces (for example in a java project).

## Second part: hexagonal architecture
Based on the project in the first part, you'll now just have to implement the adapters based on the ports (java interfaces
here) defined previously.

# 3 - Kata software architecture

## DDD 
Ubiquitous language

## BDD
Specifications by examples implemented with Cucumber

## Living documentation
Tatziki

## TDD
AssertJ + Mockito

## Hexagonal architecture
Multi modules: domain (the most important module), infrastructure and expostion for the technical ones

## Liquibase
Database version control and deployment

## Rest exposition 
Swagger2

# 4 - Help
you can start the rest expostion by executing the following command line in the exposition folder:  
**mvn clean spring-boot:run**
