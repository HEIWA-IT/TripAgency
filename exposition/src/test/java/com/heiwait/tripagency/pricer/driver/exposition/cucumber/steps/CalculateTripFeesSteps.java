package com.heiwait.tripagency.pricer.driver.exposition.cucumber.steps;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.driver.exposition.cucumber.ErrorMessagesProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CalculateTripFeesSteps {
    private final static String BUSINESS_ERRORS_CODE_KEY = "code";
    private Destination destination;
    private TravelClass travelClass;

    private Response response;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;

        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);
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

    @When("^the customer asked for the trip price")
    public void the_customer_asked_for_the_trip_price() {
        String urlTemplate = "/tripagency/api/pricer/" + destination.name() + "/travelClass/" + travelClass + "/priceTrip";
        response = given().basePath(urlTemplate).get("");
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        String computedPriceAsString =
                response.then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        Integer computedPrice = Integer.valueOf(computedPriceAsString);

        assertThat(expectedPrice).isEqualTo(computedPrice);
    }

    @Then("^the trip price returns the following message \"([^\"]*)\"$")
    public void the_trip_price_returns_the_following_message(String expectedMessage) throws JSONException {
        String responseAsString =
                response.then().statusCode(HttpStatus.NOT_FOUND.value()).and().extract().response().asString();

        JSONObject responseAsJSON = new JSONObject(responseAsString);
        String errorCode = responseAsJSON.getString(BUSINESS_ERRORS_CODE_KEY);
        String errorMessage = ErrorMessagesProperties.getErrorMessageFromErrorCode(errorCode);

        assertThat(expectedMessage).isEqualTo(errorMessage);
    }
}