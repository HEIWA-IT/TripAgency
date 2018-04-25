Feature: as a travel agency, I want to calculate travel fees depending on the departure and destination trip
  The travel price is computed using the travel fees and the agency fees added together.


  Scenario Outline: Determine the fees for a supported destination
    Given the customer want to travel to <destination>
    And the travel fees are <travelFees>€
    And the agency fees are <agencyFees>€
    When the system calculate the trip price
    Then the trip price is <travelPrice>€

    Examples:
      | destination | travelFees | agencyFees | travelPrice |
      | "Paris"     | 800        | 50         | 850         |
      | "Lille"     | 0          | 0          | 0           |
      | "New-York"  | 1000       | 100        | 1100        |
      | "Tokyo"     | 1000       | 100        | 1100        |
      | "Beijing"   | 1000       | 100        | 1100        |

  # Scenario: Determine the fees for a supported destination
  #  Given the customer want to travel to "Paris"
  #  And the travel fees are 800€
  #  And the agency fees are 50€
  #  When the system calculate the travel fees
  #  Then the travel price is 850€