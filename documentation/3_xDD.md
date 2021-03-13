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