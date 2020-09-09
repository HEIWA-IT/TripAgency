package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithJdbcTemplateRepositoryAdapter;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithJpaRepositoryAdapter;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithMockRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest
class TripResourceExceptionTestOld {
/*

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripPricerWithJpaRepositoryAdapter tripPricerWithJpaRepositoryAdapter;

    @MockBean
    private TripPricerWithJdbcTemplateRepositoryAdapter tripPricerWithJdbcTemplateRepositoryAdapter;

    @MockBean
    JdbcTemplate jdbcTemplate;

    @MockBean
    com.heiwait.tripagency.infrastructure.repository.springdata.TripJpaRepository TripJpaRepository;

    @MockBean
    private TripPricerWithMockRepositoryAdapter tripPricerWithMockRepositoryAdapter;

    //@Test
    void testPriceTripReturnAnException() {
        String urlTemplate = "/api/trip-agency/api/trip/Singapor/travelClass/BUSINESS/priceTripWithHardCodedValues";
        //Mockito.doThrow(RuntimeException.class).when(tripPricer).priceTrip(new Destination("Paris"), TravelClass.FIRST);
        Mockito.when(tripPricerWithMockRepositoryAdapter.priceTrip(new Destination("Singapor"), TravelClass.FIRST)).thenThrow(RuntimeException.class);

        try {
            mockMvc.perform(get(urlTemplate)).andExpect(status().is5xxServerError()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        /*
        Response response = given().basePath(urlTemplate).get("").then().statusCode(404).and().extract().response();
        String responseStr = response.asString();
        JSONObject obj = new JSONObject(responseStr);
        String code = obj.getString("code");
        String description = obj.getString("description");

        String expectedCode = "error.internal.server";
        String expectedDescription = "Erreur serveur!";
        assertThat(code).isEqualTo(expectedCode);
        assertThat(description).isEqualTo(expectedDescription);
        */
}