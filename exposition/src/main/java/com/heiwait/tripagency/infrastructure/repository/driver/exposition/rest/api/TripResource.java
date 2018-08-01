package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.infrastructure.application.handler.PriceComputorHandler;
import com.heiwait.tripagency.infrastructure.application.handler.PriceComputorRepositoryManager;
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
    @GetMapping(value={"/trip/{destination}/calculateTripPrice/{repositoryType}"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateTripPrice(
            @PathVariable(value="destination") String destinationName,
            @PathVariable(value="repositoryType") RepositoryType repositoryType) {
        Destination destination = new Destination(destinationName);

        Integer travelPrice = priceComputorDriverPort.computeTravelPrice(destination, repositoryType);

        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}
