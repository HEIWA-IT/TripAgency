package com.heiwait.tripagency.pricer.driver.exposition.rest.api;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pricer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PricerResource {
    private final TripPricerService tripPricer;

    public PricerResource(final TripPricerService tripPricer) {
        this.tripPricer = tripPricer;
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTrip"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTrip(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {
        Destination destination = new Destination(destinationName);
        Integer travelPrice = tripPricer.priceTrip(destination, travelClass);

        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}