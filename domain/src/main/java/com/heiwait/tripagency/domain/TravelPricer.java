package com.heiwait.tripagency.domain;

public class TravelPricer implements PriceComputorDriverPort {

    protected TripRepositoryPort tripRepository;

    public TravelPricer() {}

    public TravelPricer(final TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer computeTravelPrice(final Destination destination, final TravelClass travelClass) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);
        trip.setTravelClass(travelClass);

        return (trip.ticketPrice() * trip.travelClass().coefficient())  + trip.agencyFees() + trip.travelFees();
    }

    private void checkDestination(final Destination destination) {
        if (destination == null)
            throw new IllegalArgumentException();
    }
}