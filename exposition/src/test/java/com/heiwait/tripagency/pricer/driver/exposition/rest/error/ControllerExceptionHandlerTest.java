package com.heiwait.tripagency.pricer.driver.exposition.rest.error;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.driver.exposition.rest.api.TripPricerService;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
class ControllerExceptionHandlerTest {

    @MockBean
    TripPricerService TripPricer;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);
    }

    @Test
    void should_return_an_internal_server_error_if_a_non_business_exception_is_raised() throws JSONException {
        Mockito.when(TripPricer.priceTrip(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("This is for testing purposes"));
        String urlTemplate = "/tripagency/api/pricer/" + new Destination("Paris").name() + "/travelClass/" + TravelClass.FIRST + "/priceTrip";

        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).and().extract().response().asString();

        JSONObject obj = new JSONObject(response);
        String code = obj.getString("code");

        String expectedCode = "error.internal.server";
        assertThat(code).isEqualTo(expectedCode);
    }
}
