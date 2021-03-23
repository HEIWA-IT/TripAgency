package com.bnpparibas.hackathon.yellowteam.yellowproject.driver.exposition.rest.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
class PricerResourceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void should_find_a_trip_with_the_mock_adapter_and_a_valid_destination() {
        String urlTemplate = "/tripagency/api/pricer/Paris/travelClass/BUSINESS/priceTripWithHardCodedValues";
        String expectedTripFees = "1350";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void should_return_a_missing_destination_with_the_mock_adapter_and_a_missing_destination() throws JSONException {
        String urlTemplate = "/tripagency/api/pricer/Sydney/travelClass/BUSINESS/priceTripWithHardCodedValues";

        Response response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.NOT_FOUND.value()).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");

        String expectedCode = "error.destination.missing";
        assertThat(code).isEqualTo(expectedCode);
    }
    /**********************************************************************************************************************/
    @Test
    void findTripByDestination_should_find_a_trip_with_the_jpa_adapter_and_a_valid_destination() {
        String urlTemplate = "/tripagency/api/pricer/Paris/travelClass/BUSINESS/priceTripWithJPA";
        String expectedTripFees = "1350";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void should_return_a_missing_destination_with_the_jpa_adapter_and_a_missing_destination() throws JSONException {
        String urlTemplate = "/tripagency/api/pricer/Sydney/travelClass/BUSINESS/priceTripWithJPA";

        Response response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.NOT_FOUND.value()).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");

        String expectedCode = "error.destination.missing";
        assertThat(code).isEqualTo(expectedCode);
    }
    /**********************************************************************************************************************/
    @Test
    void should_find_a_trip_with_the_jdbc_adapter_and_a_valid_destination() {
        String urlTemplate = "/tripagency/api/pricer/Paris/travelClass/BUSINESS/priceTripWithJdbcTemplate";
        String expectedTripFees = "1350";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void should_return_a_missing_destination_with_the_jdbc_adapter_and_a_missing_destination() throws JSONException {
        String urlTemplate = "/tripagency/api/pricer/Sydney/travelClass/BUSINESS/priceTripWithJdbcTemplate";

        Response response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.NOT_FOUND.value()).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");

        String expectedCode = "error.destination.missing";
        assertThat(code).isEqualTo(expectedCode);
    }
}