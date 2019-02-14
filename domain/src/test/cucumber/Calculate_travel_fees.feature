Feature: as a travel agency, I want to calculate travel fees depending on the departure and destination trip
  The travel price is computed using the travel fees and the agency fees added together.
  Depending of the class choosen by the customer, the class value multiplicator will change:
  - for the economic one class value is 1
  - for the first one, class value is 2
  - for the business one, class value is 5

  Travel_price = (travel_ticket * class) + travel_fees + agency_fees


  Scenario Outline: Determine the fees for a supported destination
    Given the customer want to travel to <destination>
    And the customer want to travel in <travelClass> class
    And the economic travel ticket price is <ticketPrice>€
    And the travel fees are <travelFees>€
    And the agency fees are <agencyFees>€
    When the system calculate the trip price
    Then the trip price is <travelPrice>€

    Examples:
      | destination | travelClass   | ticketPrice | travelFees | agencyFees | travelPrice |
      | "Paris"     | "FIRST"       | 100         | 800        | 50         | 1050        |
      | "Lille"     | "ECONOMIC"    | 0           | 0          | 0          | 0           |
      | "New-York"  | "FIRST"       | 800         | 1000       | 100        | 2700        |
      | "Tokyo"     | "BUSINESS"    | 1200        | 1000       | 100        | 7100        |
      | "Beijing"   | "ECONOMIC"    | 1000        | 1000       | 100        | 2100        |

   Scenario: Determine the fees for travel to Paris in first class with 800€ of travel fees and 50€ of agency fees
     Given the customer want to travel to "Paris"
     And the customer want to travel in "FIRST" class
     And the economic travel ticket price is 100€
     And the travel fees are 800€
     And the agency fees are 50€
     When the system calculate the trip price
     Then the trip price is 1050€

  Scenario: Determine the fees for travel to Lille in economic class with 0€ of travel fees and 0€ of agency fees
    Given the customer want to travel to "Lille"
    And the customer want to travel in "ECONOMIC" class
    And the economic travel ticket price is 0€
    And the travel fees are 0€
    And the agency fees are 0€
    When the system calculate the trip price
    Then the trip price is 0€

  Scenario: Determine the fees for travel to New-York in first class with 800€ of travel fees and 50€ of agency fees
    Given the customer want to travel to "New-York"
    And the customer want to travel in "FIRST" class
    And the economic travel ticket price is 800€
    And the travel fees are 1000€
    And the agency fees are 100€
    When the system calculate the trip price
    Then the trip price is 2700€

  Scenario: Determine the fees for travel to Tokyo in business class with 1000€ of travel fees and 100€ of agency fees
    Given the customer want to travel to "Tokyo"
    And the customer want to travel in "BUSINESS" class
    And the economic travel ticket price is 1200€
    And the travel fees are 1000€
    And the agency fees are 100€
    When the system calculate the trip price
    Then the trip price is 7100€

  Scenario: Determine the fees for travel to Beijing in economic class with 1000€ of travel fees and 100€ of agency fees
    Given the customer want to travel to "Beijing"
    And the customer want to travel in "ECONOMIC" class
    And the economic travel ticket price is 1000€
    And the travel fees are 1000€
    And the agency fees are 100€
    When the system calculate the trip price
    Then the trip price is 2100€