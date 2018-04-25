package com.heiwait.tripagency.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelPricer implements PriceComputorDriverPort {

    private TripRepositoryPort tripRepository;

    public TravelPricer(TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer computeTravelPrice(final Destination destination) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);

        return trip.agencyFees() + trip.travelFees() ;
    }

    private void checkDestination(final Destination destination) {
        Optional<Destination> operationOpt = Optional.ofNullable(destination);
        operationOpt.orElseThrow(IllegalArgumentException::new);
    }
}
