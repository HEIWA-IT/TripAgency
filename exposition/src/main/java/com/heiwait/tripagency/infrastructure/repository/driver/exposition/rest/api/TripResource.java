package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler.PriceComputorHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class TripResource {

    private final PriceComputorHandler priceComputorHandler;
    private final TripRepositoryPort tripRepositoryMockAdapter;
    private final TripRepositoryPort tripRepositoryJdbcTemplateAdapter;
    private final TripRepositoryPort tripRepositoryJpaAdapter;

    public TripResource(
            final PriceComputorHandler priceComputorHandler,
            @Qualifier("TripRepositoryMockAdapter") final TripRepositoryPort tripRepositoryMockAdapter,
            @Qualifier("TripRepositoryJdbcTemplateAdapter") final TripRepositoryPort tripRepositoryJdbcTemplateAdapter,
            @Qualifier("TripRepositoryJpaAdapter") final TripRepositoryPort tripRepositoryJpaAdapter) {
        this.priceComputorHandler = priceComputorHandler;
        this.tripRepositoryMockAdapter = tripRepositoryMockAdapter;
        this.tripRepositoryJdbcTemplateAdapter = tripRepositoryJdbcTemplateAdapter;
        this.tripRepositoryJpaAdapter = tripRepositoryJpaAdapter;
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/trip/{destination}/travelClass/{travelClass}/priceTripWithHardCodedValues"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithHardCodedValues(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, tripRepositoryMockAdapter);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/trip/{destination}/travelClass/{travelClass}/priceTripWithJPA"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJPA(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, tripRepositoryJpaAdapter);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/trip/{destination}/travelClass/{travelClass}/priceTripWithJdbcTemplate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJdbcTemplate(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, tripRepositoryJdbcTemplateAdapter);
    }

    private ResponseEntity<Integer> priceTrip(final String destinationName, final TravelClass travelClass,
                                              final TripRepositoryPort repositoryType) {
        Destination destination = new Destination(destinationName);
        priceComputorHandler.setTripRepository(repositoryType);
        Integer travelPrice = priceComputorHandler.priceTrip(destination, travelClass);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}