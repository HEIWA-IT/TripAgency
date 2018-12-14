package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.infrastructure.application.handler.PriceComputorHandler;
import com.heiwait.tripagency.infrastructure.application.handler.RepositoryType;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class TripResource {

    private final PriceComputorHandler priceComputorDriverPort;

    public TripResource(final PriceComputorHandler priceComputorDriverPort) {
        this.priceComputorDriverPort = priceComputorDriverPort;
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/calculateTripPriceWithHardCodedValues"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateTripPriceWithHardCodedValues(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return calculateTripPrice(destinationName, travelClass, RepositoryType.MOCK);
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/calculateTripPriceWithJPA"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateTripPriceWithJPA(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return calculateTripPrice(destinationName, travelClass, RepositoryType.JPA);
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/calculateTripPricewithJdbcTemplate/"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateTripPricewithJdbcTemplate(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return calculateTripPrice(destinationName, travelClass, RepositoryType.JDBC_TEMPLATE);
    }

    private ResponseEntity<Integer> calculateTripPrice(final String destinationName, final TravelClass travelClass,
                                                       final RepositoryType repositoryType) {
        Destination destination=new Destination(destinationName);
        Integer travelPrice=priceComputorDriverPort.computeTravelPrice(destination, travelClass, repositoryType);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}
