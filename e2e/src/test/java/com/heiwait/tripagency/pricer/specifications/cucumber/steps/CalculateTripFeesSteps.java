package com.heiwait.tripagency.pricer.specifications.cucumber.steps;

import com.heiwait.tripagency.pricer.specifications.cucumber.ErrorMessagesProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTripFeesSteps {

    String HOST;
    String BASE_URL;
    RequestSpecification request;
    Response response;

    private String destination;
    private String travelClass;

    @Before
    public void setup() {
        Map<String, String> env = System.getenv();
        HOST = env.get("HOST");
        BASE_URL = HOST+"/tripagency/";
        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
    }

    @Given("^the customer wants to travel to \"([^\"]*)\"$")
    public void the_customer_wants_to_travel_to(String dest) {
        destination = dest;
    }

    @Given("^the customer wants to travel in \"([^\"]*)\" class$")
    public void the_customer_wants_to_travel_in_class(String travelClass) {
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
        String url = "api/pricer/" + destination + "/travelClass/" + travelClass + "/priceTrip";
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