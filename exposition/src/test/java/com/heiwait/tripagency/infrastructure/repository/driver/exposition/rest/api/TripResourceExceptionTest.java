package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithMockRepositoryAdapter;
import com.heiwait.tripagency.infrastructure.repository.mock.TripRepositoryMockAdapter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
class TripResourceExceptionTest {

    @LocalServerPort
    int port;

    @MockBean
    TripPricerWithMockRepositoryAdapter tripPricer;

    @MockBean
    TripRepositoryMockAdapter tripRepository;


    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    //@Test
    void testPriceTripReturnAnException() throws JSONException {
        String urlTemplate = "/trip-agency/api/trip/Paris/travelClass/BUSINESS/priceTripWithHardCodedValues";

        Mockito.doThrow(RuntimeException.class).when(tripPricer).priceTrip(new Destination("Paris"), TravelClass.BUSINESS);
        //Mockito.when(tripPricer.priceTrip(new Destination("Paris"), TravelClass.FIRST)).thenThrow(RuntimeException.class);

        Response response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");
        String description = obj.getString("description");

        String expectedCode = "error.internal.server";
        String expectedDescription = "Erreur serveur!";
        assertThat(code).isEqualTo(expectedCode);
        assertThat(description).isEqualTo(expectedDescription);
    }

}