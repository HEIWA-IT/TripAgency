package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithJdbcTemplateRepositoryAdapter;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithJpaRepositoryAdapter;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.TripPricerWithMockRepositoryAdapter;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pricer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TripResource {

    private final TripPricerWithMockRepositoryAdapter tripPricerWithMockRepositoryAdapter;
    private final TripPricerWithJpaRepositoryAdapter tripPricerWithJpaRepositoryAdapter;
    private final TripPricerWithJdbcTemplateRepositoryAdapter tripPricerWithJdbcTemplateRepositoryAdapter;

    public TripResource(
            final TripPricerWithMockRepositoryAdapter tripPricerWithMockRepositoryAdapter,
            final TripPricerWithJpaRepositoryAdapter tripPricerWithJpaRepositoryAdapter,
            final TripPricerWithJdbcTemplateRepositoryAdapter tripPricerWithJdbcTemplateRepositoryAdapter) {
        this.tripPricerWithMockRepositoryAdapter = tripPricerWithMockRepositoryAdapter;
        this.tripPricerWithJpaRepositoryAdapter = tripPricerWithJpaRepositoryAdapter;
        this.tripPricerWithJdbcTemplateRepositoryAdapter = tripPricerWithJdbcTemplateRepositoryAdapter;
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithHardCodedValues"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithHardCodedValues(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        Destination destination = new Destination(destinationName);
        Integer travelPrice = tripPricerWithMockRepositoryAdapter.priceTrip(destination, travelClass);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithJPA"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJPA(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        Destination destination = new Destination(destinationName);
        Integer travelPrice = tripPricerWithJpaRepositoryAdapter.priceTrip(destination, travelClass);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithJdbcTemplate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJdbcTemplate(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        Destination destination = new Destination(destinationName);
        Integer travelPrice = tripPricerWithJdbcTemplateRepositoryAdapter.priceTrip(destination, travelClass);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}