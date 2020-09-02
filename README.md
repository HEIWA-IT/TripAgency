#Trip Agency sample

# 1 - Kata goals
This sample has many goals :
- to teach the basis of automated tests based on BDD (Behaviour Driven Development)
- to generate a living documentation based ont the tests
- to use the hexagonal architecture, an architectural pattern that allows dissociating the domain code from the technical code.
- to use best building tools practices: use of BOM (Bills Of Materials) and wrapper (MavenWrapper and GradleWrapper in this sample)
- to use i18n (internationalization)
- to use best practices for CI/CD pipelines (shell scripts in the project to be used in the different CI servers)
- to use a database managing tools (liquibase here)

# 2 - Goals explanation


# 3 - Make it work
you can start the rest exposition by executing the following command line in the exposition folder:  
**mvn clean spring-boot:run**  

The url to use the service is the following:  
**http://localhost:12378/trip-agency/swagger-ui/**

# 4 - Kata solution software architecture
## DDD
Ubiquitous language

## BDD
Specifications by examples implemented with Cucumber

## Living documentation
Cukedoctor  
See in the following repository in the domain module : domain/target/TripAgency   
You should see a pdf file containing the results of the cucumber tests.

## Testing tools
AssertJ + Mockito + Cucumber

## Hexagonal architecture
Multi modules: 
- domain (the most important module)
- infrastructure (infrastructure repository) and exposition (infrastructure driver) for the technical ones

## Liquibase
Database version control and deployment

## Rest exposition 
Swagger2
