package com.heiwait.tripagency.pricer.application;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.TravelClass;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.domain.TripPricer;
import com.heiwait.tripagency.pricer.driven.repository.TripRepositoryType;
import com.heiwait.tripagency.pricer.driven.repository.TripRepositoryHandler;
import org.springframework.stereotype.Service;

@Service
public class PricerApplication {
    private final TripPricer pricer = new TripPricer();
    private final TripRepositoryHandler tripRepositoryHandler;

    public PricerApplication(TripRepositoryHandler tripRepositoryHandler) {
        this.tripRepositoryHandler = tripRepositoryHandler;
    }

    public Integer priceTrip(String destinationName, TravelClass travelClass, TripRepositoryType tripRepositoryType){
        Destination destination = new Destination(destinationName);
        Trip trip = tripRepositoryHandler.findTripByDestination(destination, tripRepositoryType);
        return pricer.priceTrip(travelClass, trip);
    }
}