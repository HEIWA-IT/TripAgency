package com.heiwait.tripagency.pricer.specifications.cucumber.steps;

import com.heiwait.tripagency.pricer.specifications.cucumber.ErrorMessagesProperties;
import com.heiwait.tripagency.pricer.domain.*;
import com.heiwait.tripagency.pricer.domain.error.BusinessException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.*;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTripFeesSteps {

    @Mock
    private TripRepositoryPort tripRepositoryPort;
    @InjectMocks
    private TripPricer tripPricer;

    private Destination destination;
    private TravelClass travelClass;
    private Integer agencyFees;
    private Integer stayFees;
    private Integer ticketPrice;

    private Integer computedPrice;
    private String errorMessage;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("^the customer wants to travel to \"([^\"]*)\"$")
    public void the_customer_wants_to_travel_to(String dest) {
        destination = new Destination(dest);
    }

    @Given("^the customer wants to travel in \"([^\"]*)\" class$")
    public void the_customer_wants_to_travel_in_class(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Given("^the economic travel ticket price is (\\d+)€$")
    public void the_economic_travel_ticket_price_is_€(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Given("^the stay fees are (\\d+)€$")
    public void the_stay_fees_are_€(Integer stayFees) {
        this.stayFees = stayFees;
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
        this.agencyFees = agencyFees;
    }

    @When("^the customer asked for the trip price")
    public void the_customer_asked_for_the_trip_price() {
        Mockito.when(tripRepositoryPort.findTripByDestination(destination)).thenReturn(trip());

        try {
            computedPrice = tripPricer.priceTrip(destination, travelClass);
        } catch (BusinessException be) {
            Locale usLocale = new Locale("en", "US");
            Locale.setDefault(usLocale);
            errorMessage = ErrorMessagesProperties.getErrorMessageFromErrorCode(be.error().code());
        }
    }

    private Trip trip() {
        if (destination.name().equals("Sydney")) {
            return Trip.Builder.MISSING_DESTINATION;
        } else {
            return new Trip.Builder().with(builder -> {
                builder.setAgencyFees(this.agencyFees);
                builder.setStayFees(this.stayFees);
                builder.setTicketPrice(this.ticketPrice);
            }).build();
        }
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        assertThat(expectedPrice).isEqualTo(computedPrice);
    }

    @Then("^the trip price returns the following message \"([^\"]*)\"$")
    public void the_trip_price_returns_the_following_message(String expectedMessage) {
        assertThat(expectedMessage).isEqualTo(errorMessage);
    }
}