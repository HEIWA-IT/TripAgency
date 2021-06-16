Feature: as a travel agency, I want to calculate travel fees depending on the departure and destination trip.
  To facilitate the use case, we will use always the same departure Lille.
  We will only need the destination.
  The trip pricer computes the total price of the trip using the ticket price, the stay fees (hotel, activities, ...)
  and the agency fees added together.

  The ticket price defined is always based on the economic one.
  Depending of the class chosen by the customer, the travel class value multiplier factor will change:
  - for the economic one class value is 1
  - for the first one, class value is 2
  - for the business one, class value is 5

  If the destination wanted is not know by the pricer, it returns a missing destination message

  Rule 1: Trip_price = (ticket_price * travel_class) + stay_fees + agency_fees
  Rule 2: If the destination wanted by the customer is not in the system, the pricer returns a missing destination message.

  Scenario Outline: The pricer determines the price for supported destinations
    Given the customer wants to travel to <destination>
    And the economic travel ticket price is <ticketPrice>€
    And the customer wants to travel in <travelClass> class
    And the stay fees are <stayFees>€
    And the agency fees are <agencyFees>€
    When the customer asked for the trip price
    Then the trip price is <tripPrice>€

    Examples:
      | destination | travelClass | ticketPrice | stayFees | agencyFees | tripPrice |
      | "Lille"     | "ECONOMIC"  | 0           | 0        | 0          | 0         |
      | "Paris"     | "FIRST"     | 100         | 800      | 50         | 1050      |
      | "New-York"  | "FIRST"     | 800         | 1000     | 100        | 2700      |
      | "Tokyo"     | "BUSINESS"  | 1200        | 1000     | 100        | 7100      |
      | "Beijing"   | "ECONOMIC"  | 1000        | 1000     | 100        | 2100      |

  Scenario: The pricer returns a missing destination message when the destination is not known in the system
    Given the customer wants to travel to "Sydney"
    And the customer wants to travel in "FIRST" class
    When the customer asked for the trip price
    Then the trip price returns the following message "Missing destination!"

  Scenario: The pricer determines a price of 0€ to travel to Lille in ECONOMIC class
    Given the customer wants to travel to "Lille"
    And the economic travel ticket price is 0€
    And the customer wants to travel in "ECONOMIC" class
    And the stay fees are 0€
    And the agency fees are 0€
    When the customer asked for the trip price
    Then the trip price is 0€

  Scenario: The pricer determines a price of 1050€ to travel to Paris in FIRST class
    Given the customer wants to travel to "Paris"
    And the economic travel ticket price is 100€
    And the customer wants to travel in "FIRST" class
    And the stay fees are 800€
    And the agency fees are 50€
    When the customer asked for the trip price
    Then the trip price is 1050€

  Scenario: The pricer determines a price of 2700€ to travel to New-York in FIRST class
    Given the customer wants to travel to "New-York"
    And the economic travel ticket price is 800€
    And the customer wants to travel in "FIRST" class
    And the stay fees are 1000€
    And the agency fees are 100€
    When the customer asked for the trip price
    Then the trip price is 2700€

  Scenario: The pricer determines a price of 7100€ to travel to Tokyo in BUSINESS class
    Given the customer wants to travel to "Tokyo"
    And the economic travel ticket price is 1200€
    And the customer wants to travel in "BUSINESS" class
    And the stay fees are 1000€
    And the agency fees are 100€
    When the customer asked for the trip price
    Then the trip price is 7100€

  Scenario: The pricer determines a price of 2100€ to travel to Beijing in ECONOMIC class
    Given the customer wants to travel to "Beijing"
    And the economic travel ticket price is 1000€
    And the customer wants to travel in "ECONOMIC" class
    And the stay fees are 1000€
    And the agency fees are 100€
    When the customer asked for the trip price
    Then the trip price is 2100€