package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.api;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.PriceComputorDriverPort;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class TripResource {

    private final PriceComputorDriverPort priceComputorDriverPort;

    public TripResource(final PriceComputorDriverPort priceComputorDriverPort) {
        this.priceComputorDriverPort = priceComputorDriverPort;
    }

    @ApiOperation(value="Compute travel fees", notes="Returns the price of a trip")
    @GetMapping(value={"/trip/{destination}/calculateTripPrice"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculateTripPrice(
            @PathVariable(value="destination") String destinationName) {
        Destination destination = new Destination(destinationName);
        Integer travelPrice = priceComputorDriverPort.computeTravelPrice(destination);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}
