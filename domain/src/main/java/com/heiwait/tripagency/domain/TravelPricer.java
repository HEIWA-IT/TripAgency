package com.heiwait.tripagency.domain;

public class TravelPricer implements PriceComputorDriverPort {

    protected TripRepositoryPort tripRepository;

    public TravelPricer() {}

    public TravelPricer(final TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer priceTravel(final Destination destination, final TravelClass travelClass) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);

        return priceTrip(travelClass, trip);
    }

    private int priceTrip(TravelClass travelClass, Trip trip) {
        return (trip.ticketPrice() * travelClass.coefficient())  + trip.agencyFees() + trip.travelFees();
    }

    private void checkDestination(final Destination destination) {
        if (destination == null)
            throw new IllegalArgumentException();
    }
}