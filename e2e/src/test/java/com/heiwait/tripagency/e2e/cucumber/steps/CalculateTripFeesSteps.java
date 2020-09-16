package com.heiwait.tripagency.e2e.cucumber.steps;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.e2e.cucumber.ErrorMessagesProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTripFeesSteps {

    String BASE_URL = "http://localhost:12378/trip-agency/";
    RequestSpecification request;
    Response response;

    private Destination destination;
    private TravelClass travelClass;

    @Before
    public void setup() {
        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
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
    }

    @Given("^the stay fees are (\\d+)€$")
    public void the_stay_fees_are_€(Integer stayFees) {
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
    }

    @When("^the system calculate the trip price")
    public void the_system_calculate_the_trip_price() {
        String url = "api/trip/" + destination.name() + "/travelClass/" + travelClass + "/priceTripWithJPA";
        response = request.get(url);
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        assertThat(expectedPrice).isEqualTo(Integer.parseInt(response.asString()));
    }

    @Then("^the trip price returns the following message \"([^\"]*)\"$")
    public void the_trip_price_returns_the_following_message(String expectedMessage) {
        String errorMessage = errorMessageFromResponse(response);
        assertThat(expectedMessage).isEqualTo(errorMessage);
    }

    private String errorMessageFromResponse(Response response) {
        String jsonString = response.asString();
        String code = JsonPath.from(jsonString).get("code");

        return ErrorMessagesProperties.getErrorMessageFromErrorCode(code);
    }
}