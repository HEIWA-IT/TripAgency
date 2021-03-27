## 0 Sample goals
This sample was made to provide a concrete example on some best practices of the market regarding a java application
exposing a Rest API.  
Everything may be not perfect. This sample will always be on WIP.  
Be kind and don't hesitate to do some PR/MR ^^  
Everyone is welcome!

This sample has many goals:
- to use best building tools practices: use of BOM (Bill Of Materials) and wrapper (MavenWrapper and GradleWrapper in this sample)
- to teach the basis of automated tests with Cucumber (third phase of Behaviour Driven Development)
- to generate a living documentation based on the tests
- to use either the hexagonal architecture (an architectural pattern that allows dissociating the domain code from the technical
  code and ease also the tests implementation without infrastructure) or the functional core - imperative shell architecture
- to use i18n (internationalization)
- to use best practices for CI/CD pipelines (shell scripts in the project to be used in the different CI servers)
- to use a database managing tools (liquibase here)