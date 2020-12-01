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

  Scenario Outline: Determine the fees for a supported destination
    Given the customer wants to travel to <destination>
    And the economic travel ticket price is <ticketPrice>€
    And the customer wants to travel in <travelClass> class
    And the stay fees are <stayFees>€
    And the agency fees are <agencyFees>€
    When the customer asked for the trip price
    Then the trip price is <tripPrice>€

    Examples:
      | destination | travelClass | agencyFees | stayFees | ticketPrice | tripPrice |
      | "Lille"     | "ECONOMIC"  | 0          | 0        | 0           | 0         |
      | "Paris"     | "FIRST"     | 50         | 300      | 200         | 750       |
      | "Beijing"   | "ECONOMIC"  | 100        | 1000     | 1200        | 2300      |
      | "New-York"  | "FIRST"     | 150        | 1500     | 1000        | 3650      |
      | "Tokyo"     | "BUSINESS"  | 200        | 2000     | 1500        | 9700      |

  Scenario: returns a missing destination message when the destination is missing
    Given the customer wants to travel to "Sydney"
    And the customer wants to travel in "FIRST" class
    When the customer asked for the trip price
    Then the trip price returns the following message "Missing destination!"

  Scenario: Determine the fees for travel to Lille in economic class
    Given the customer wants to travel to "Lille"
    And the economic travel ticket price is 0€
    And the customer wants to travel in "ECONOMIC" class
    And the stay fees are 0€
    And the agency fees are 0€
    When the customer asked for the trip price
    Then the trip price is 0€

  Scenario: Determine the fees for travel to Paris in first class
    Given the customer wants to travel to "Paris"
    And the economic travel ticket price is 200€
    And the customer wants to travel in "FIRST" class
    And the stay fees are 300€
    And the agency fees are 50€
    When the customer asked for the trip price
    Then the trip price is 750€

  Scenario: Determine the fees for travel to Beijing in economic class
    Given the customer wants to travel to "Beijing"
    And the economic travel ticket price is 1200€
    And the customer wants to travel in "ECONOMIC" class
    And the stay fees are 1000€
    And the agency fees are 100€
    When the customer asked for the trip price
    Then the trip price is 2300€

  Scenario: Determine the fees for travel to New-York in first class
    Given the customer wants to travel to "New-York"
    And the economic travel ticket price is 1000€
    And the customer wants to travel in "FIRST" class
    And the stay fees are 1500€
    And the agency fees are 150€
    When the customer asked for the trip price
    Then the trip price is 3650€

  Scenario: Determine the fees for travel to Tokyo in business class
    Given the customer wants to travel to "Tokyo"
    And the economic travel ticket price is 1500€
    And the customer wants to travel in "BUSINESS" class
    And the stay fees are 2000€
    And the agency fees are 200€
    When the customer asked for the trip price
    Then the trip price is 9700€