package com.heiwait.tripagency.domain.cucumber.steps;

import com.heiwait.tripagency.domain.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTravelFeesSteps {

    @Mock
    private TripRepositoryPort tripRepositoryPort;
    @InjectMocks
    private TravelPricer travelPricer;

    private Trip trip = new Trip();
    private Destination destination = new Destination();
    private TravelClass travelClass;

    private Integer computedPrice;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Given("^the customer want to travel to \"([^\"]*)\"$")
    public void the_customer_want_to_travel_to(String dest) {
        destination.setName(dest);
        trip.setDestination(destination);
        Mockito.when(tripRepositoryPort.findTripByDestination(destination)).thenReturn(trip);
    }

    @Given("^the customer want to travel in \"([^\"]*)\" class$")
    public void the_customer_want_to_travel_in_class(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Given("^the economic travel ticket price is (\\d+)€$")
    public void the_economic_travel_ticket_price_is_€(int ticketPrice) {
       trip.setTicketPrice(ticketPrice);
    }

    @Given("^the travel fees are (\\d+)€$")
    public void the_travel_fees_are_€(Integer travelFees) {
        trip.setTravelFees(travelFees);
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
        trip.setAgencyFees(agencyFees);
    }

    @When("^the system calculate the trip price")
    public void the_system_calculate_the_trip_price() {
        computedPrice = travelPricer.computeTravelPrice(destination, travelClass);
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        assertThat(expectedPrice).isEqualTo(computedPrice);
    }
}
