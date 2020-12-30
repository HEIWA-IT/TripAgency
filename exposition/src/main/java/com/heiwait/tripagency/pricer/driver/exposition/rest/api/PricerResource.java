package com.heiwait.tripagency.pricer.driver.exposition.rest.api;

import com.heiwait.tripagency.pricer.application.PricerApplication;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.driven.repository.TripRepositoryType;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pricer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PricerResource {

    private final PricerApplication pricerApplication;

    public PricerResource(
            PricerApplication pricerApplication) {
        this.pricerApplication = pricerApplication;
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithHardCodedValues"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithHardCodedValues(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {
        Integer travelPrice = pricerApplication.priceTrip(destinationName, travelClass, TripRepositoryType.MOCK);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithJPA"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJPA(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {
        Integer travelPrice = pricerApplication.priceTrip(destinationName, travelClass, TripRepositoryType.JPA);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }

    @ApiOperation(value = "Compute travel fees", notes = "Returns the price of a trip")
    @GetMapping(value = {"/{destination}/travelClass/{travelClass}/priceTripWithJdbcTemplate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> priceTripWithJdbcTemplate(
            @PathVariable(value = "destination") String destinationName,
            @PathVariable(value = "travelClass") TravelClass travelClass) {
        Integer travelPrice = pricerApplication.priceTrip(destinationName, travelClass, TripRepositoryType.JDBC);
        return new ResponseEntity<>(travelPrice, HttpStatus.OK);
    }
}