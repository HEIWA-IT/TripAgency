package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

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

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
class TripResourceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testPriceTripWithHardCodedCallForParis() {
        String urlTemplate = "/trip-agency/api/trip/Paris/travelClass/BUSINESS/priceTripWithHardCodedValues";
        String expectedTripFees = "1025";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void testPriceTripWithJPACallForParis() {
        String urlTemplate = "/trip-agency/api/trip/Paris/travelClass/BUSINESS/priceTripWithJPA";
        String expectedTripFees = "1350";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void testPriceTripWithJdbcTemplatedCallForParis() {
        String urlTemplate = "/trip-agency/api/trip/Paris/travelClass/BUSINESS/priceTripWithJdbcTemplate";
        String expectedTripFees = "1350";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.OK.value()).and().extract().response().asString();

        assertThat(response).isEqualTo(expectedTripFees);
    }

    @Test
    void testPriceTripWithJdbcTemplatedCallForSydney() throws JSONException {
        String urlTemplate = "/trip-agency/api/trip/Sydney/travelClass/BUSINESS/priceTripWithJdbcTemplate";

        Response response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.NOT_FOUND.value()).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");
        String description = obj.getString("description");

        String expectedCode = "error.destination.missing";
        String expectedDescription = "Destination manquante!";
        assertThat(code).isEqualTo(expectedCode);
        assertThat(description).isEqualTo(expectedDescription);
    }
}