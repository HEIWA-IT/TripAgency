package com.heiwait.tripagency.pricer.specifications.cucumber.steps;

import com.heiwait.tripagency.pricer.driver.exposition.ExpositionApplication;
import com.heiwait.tripagency.pricer.specifications.cucumber.ErrorMessagesProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.net.HttpURLConnection;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@ContextConfiguration(classes = ExpositionApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CalculateTripFeesSteps {
    private final static String BUSINESS_ERRORS_CODE_KEY = "code";
    private String destination;
    private String travelClass;
    private Response response;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);

        RestAssured.port = port;
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
        String urlTemplate = "/tripagency/api/pricer/" + destination + "/travelClass/" + travelClass + "/priceTrip";
        response = given().basePath(urlTemplate).get("");
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        String computedPriceAsString =
                response.then().statusCode(HttpURLConnection.HTTP_OK).and().extract().response().asString();

        Integer computedPrice = Integer.valueOf(computedPriceAsString);

        assertThat(expectedPrice).isEqualTo(computedPrice);
    }

    @Then("^the trip price returns the following message \"([^\"]*)\"$")
    public void the_trip_price_returns_the_following_message(String expectedMessage){
        String responseAsString =
                response.then().statusCode(HttpURLConnection.HTTP_NOT_FOUND).and().extract().response().asString();

        String errorMessage = errorMessageFromResponse(responseAsString);

        assertThat(expectedMessage).isEqualTo(errorMessage);
    }

    private String errorMessageFromResponse(String responseAsString){
        String errorCode = JsonPath.from(responseAsString).get(BUSINESS_ERRORS_CODE_KEY);
        return ErrorMessagesProperties.getErrorMessageFromErrorCode(errorCode);
    }
}