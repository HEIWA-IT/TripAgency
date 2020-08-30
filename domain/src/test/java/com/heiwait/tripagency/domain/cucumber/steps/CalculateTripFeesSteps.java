package com.heiwait.tripagency.domain.cucumber.steps;

import com.heiwait.tripagency.domain.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTripFeesSteps {

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


    @Given("^the customer wants to travel to \"([^\"]*)\"$")
    public void the_customer_wants_to_travel_to(String dest) {
        destination.setName(dest);
        trip.setDestination(destination);
        Mockito.when(tripRepositoryPort.findTripByDestination(destination)).thenReturn(trip);
    }

    @Given("^the customer wants to travel in \"([^\"]*)\" class$")
    public void the_customer_wants_to_travel_in_class(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Given("^the economic travel ticket price is (\\d+)€$")
    public void the_economic_travel_ticket_price_is_€(int ticketPrice) {
        trip.setTicketPrice(ticketPrice);
    }

    @Given("^the activities fees are (\\d+)€$")
    public void the_activities_fees_are_€(Integer travelFees) {
        trip.setTravelFees(travelFees);
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
        trip.setAgencyFees(agencyFees);
    }

    @When("^the system calculate the trip price")
    public void the_system_calculate_the_trip_price() {
        computedPrice = travelPricer.priceTravel(destination, travelClass);
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        assertThat(expectedPrice).isEqualTo(computedPrice);
    }
}