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
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/priceTripWithHardCodedValues"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithHardCodedValues(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, RepositoryType.MOCK);
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/priceTripWithJPA"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJPA(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, RepositoryType.JPA);
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/travelClass/{travelClass}/priceTripWithJdbcTemplate"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJdbcTemplate(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="travelClass") TravelClass travelClass) {

        return priceTrip(destinationName, travelClass, RepositoryType.JDBC_TEMPLATE);
    }

    private ResponseEntity<Integer> priceTrip(final String destinationName, final TravelClass travelClass,
                                              final RepositoryType repositoryType) {
        Destination destination=new Destination(destinationName);
        Integer travelPrice=priceComputorDriverPort.priceTravel(destination, travelClass, repositoryType);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}