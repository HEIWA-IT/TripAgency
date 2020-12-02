package com.heiwait.tripagency.pricer.application.cucumber.steps;

import com.heiwait.tripagency.pricer.application.PricerHandler;
import com.heiwait.tripagency.pricer.application.config.AppConfig;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.domain.error.ErrorMessagesProperties;
import com.heiwait.tripagency.pricer.domain.error.BusinessException;
import com.heiwait.tripagency.pricer.driven.repository.TripRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(classes = {AppConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
public class CalculateTripFeesSteps {
    private final PricerHandler priceHandler;

    public CalculateTripFeesSteps(PricerHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    private String destination;
    private TravelClass travelClass;

    private Integer computedPrice;
    private String errorMessage;

    @Given("^the customer wants to travel to \"([^\"]*)\"$")
    public void the_customer_wants_to_travel_to(String dest) {
        destination = dest;
    }

    @Given("^the customer wants to travel in \"([^\"]*)\" class$")
    public void the_customer_wants_to_travel_in_class(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    @Given("^the economic travel ticket price is (\\d+)€$")
    public void the_economic_travel_ticket_price_is_€(int ticketPrice) {

    }

    @Given("^the stay fees are (\\d+)€$")
    public void the_stay_fees_are_€(Integer stayFees) {
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
    }

    @When("^the customer asked for the trip price")
    public void the_customer_asked_for_the_trip_price() {

        try {
            computedPrice = priceHandler.priceTripWithHardCodedValues(destination, travelClass);
        } catch (BusinessException be) {
            Locale usLocale = new Locale("en", "US");
            Locale.setDefault(usLocale);
            errorMessage = ErrorMessagesProperties.getErrorMessageFromErrorCode(be.error().code());
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