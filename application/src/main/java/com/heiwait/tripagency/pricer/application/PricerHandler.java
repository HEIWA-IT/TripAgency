package com.heiwait.tripagency.pricer.application;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.domain.TripPricer;
import com.heiwait.tripagency.pricer.driven.repository.TripRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PricerHandler {
    private final TripPricer pricer = new TripPricer();
    private TripRepository tripRepository;

    private final TripRepository tripRepositoryMock;
    private final TripRepository tripRepositoryJdbcTemplate;
    private final TripRepository tripRepositoryJpa;

    public PricerHandler(
            final @Qualifier("TripRepositoryMock") TripRepository tripRepositoryMock,
            final @Qualifier("TripRepositoryJdbcTemplate") TripRepository tripRepositoryJdbcTemplate,
            final @Qualifier("TripRepositoryJpa") TripRepository tripRepositoryJpa) {
        this.tripRepositoryMock = tripRepositoryMock;
        this.tripRepositoryJdbcTemplate = tripRepositoryJdbcTemplate;
        this.tripRepositoryJpa = tripRepositoryJpa;
    }

    public Integer priceTripWithHardCodedValues(String destinationName, TravelClass travelClass) {
        setTripRepository(tripRepositoryMock);
        return priceTrip(destinationName, travelClass);
    }

    public Integer priceTripWithJdbcTemplate(String destinationName, TravelClass travelClass) {
        setTripRepository(tripRepositoryJdbcTemplate);
        return priceTrip(destinationName, travelClass);
    }

    public Integer priceTripWithJpa(String destinationName, TravelClass travelClass) {
        setTripRepository(tripRepositoryJpa);
        return priceTrip(destinationName, travelClass);
    }

    private Integer priceTrip(String destinationName, TravelClass travelClass){
        Destination destination = new Destination(destinationName);
        Trip trip = tripRepository().findTripByDestination(destination);
        return pricer.priceTrip(travelClass, trip);
    }

    public TripRepository tripRepository() {
        return tripRepository;
    }

    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
}