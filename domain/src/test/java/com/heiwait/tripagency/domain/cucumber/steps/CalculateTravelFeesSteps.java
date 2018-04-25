package com.heiwait.tripagency.domain.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTravelFeesSteps {
    @Given("^the customer want to travel to \"([^\"]*)\"$")
    public void the_customer_want_to_travel_to(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the travel fees are (\\d+)€$")
    public void the_travel_fees_are_€(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the agency fees are (\\d+)€$")
    public void the_agency_fees_are_€(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the system calculate the trip price$")
    public void the_system_calculate_the_trip_price() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the trip price is (\\d+)€$")
    public void the_trip_price_is_€(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
