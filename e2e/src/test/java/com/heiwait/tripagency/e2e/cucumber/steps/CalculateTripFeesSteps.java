package com.heiwait.tripagency.e2e.cucumber.steps;


import com.heiwait.tripagency.domain.TravelClass;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTripFeesSteps {

    String BASE_URL = "http://localhost:12378/trip-agency/api/trip/{{destination}}/travelClass/{{travelClass}}/priceTripWithHardCodedValues";
    RequestSpecification request;
    Response response;

    String destination;
    TravelClass travelClass;

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
    }

    @Given("^the customer wants to travel to \"([^\"]*)\"$")
    public void the_customer_wants_to_travel_to(String dest) {
        /*
        String jsonString = response.asString();
        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");

        assertThat(books.size()).isPositive();

        String bookId = books.get(0).get("isbn");
        */

    }

    @Given("^the customer wants to travel in \"([^\"]*)\" class$")
    public void the_customer_wants_to_travel_in_class() {
        throw new PendingException();
    }

    @Given("^the economic travel ticket price is (\\d+)€$")
    public void the_economic_travel_ticket_price_is_€(int ticketPrice) {
        throw new PendingException();
    }

    @Given("^the stay fees are (\\d+)€$")
    public void the_stay_fees_are_€(Integer stayFees) {
        throw new PendingException();
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(Integer agencyFees) {
        throw new PendingException();
    }

    @When("^the system calculate the trip price")
    public void the_system_calculate_the_trip_price() {
        response = request.get("/BookStore/v1/Books");
    }


    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(Integer expectedPrice) {
        throw new PendingException();
    }

    @Then("^the trip price returns the following message \"([^\"]*)\"$")
    public void the_trip_price_returns_the_following_message(String expectedMessage) {
        throw new PendingException();
    }
}