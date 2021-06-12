package com.heiwait.tripagency.pricer.driver.exposition.tu;

import com.heiwait.tripagency.pricer.domain.*;
import com.heiwait.tripagency.pricer.driver.exposition.rest.api.PricerResource;
import com.heiwait.tripagency.pricer.driver.exposition.rest.api.TripPricerService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.net.ConnectException;
import java.util.Locale;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class ControllerExceptionHandlerTest {

    @MockBean
    TripPricerService TripPricer;
    @InjectMocks
    PricerResource pricerReosurce;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        Locale usLocale = new Locale("en", "US");
        Locale.setDefault(usLocale);

        //MockitoAnnotations.openMocks(this);
        //RestAssuredMockMvc.mockMvc = MockMvcBuilders.standaloneSetup(transactions).build();
        Mockito.when(TripPricer.priceTrip(Mockito.any(), Mockito.any())).thenThrow(RuntimeException.class);
    }

    @Test
    void computeTravelPrice_should_return_an_invalid_argument_exception_if_destination_is_null() {
        String urlTemplate = "/tripagency/api/pricer/" + new Destination("Paris").name() + "/travelClass/" + TravelClass.FIRST + "/priceTrip";
        String response = given().basePath(urlTemplate).get("").then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).and().extract().response().asString();
        System.out.println("the Response " + response);

        /*assertThatThrownBy(() -> travelPricer.priceTrip(null, null))
                .isInstanceOf(IllegalArgumentException.class);*/
    }
}
