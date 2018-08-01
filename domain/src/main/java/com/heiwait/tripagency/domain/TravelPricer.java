package com.heiwait.tripagency.domain;

import java.util.Optional;

public class TravelPricer implements PriceComputorDriverPort {

    protected TripRepositoryPort tripRepository;

    public TravelPricer() {}

    public TravelPricer(final TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer computeTravelPrice(final Destination destination) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);

        return trip.agencyFees() + trip.travelFees();
    }

    private void checkDestination(final Destination destination) {
        Optional<Destination> operationOpt = Optional.ofNullable(destination);
        operationOpt.orElseThrow(IllegalArgumentException::new);
    }
}