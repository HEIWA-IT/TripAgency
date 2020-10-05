package com.heiwait.tripagency.domain;

import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;

public class TripPricer implements PriceComputorDriverPort {

    protected TripRepositoryPort tripRepository;

    public TripPricer(final TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer priceTrip(final Destination destination, final TravelClass travelClass) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);

        if (TripBuilder.MISSING_DESTINATION.equals(trip)) {
            throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return priceTrip(travelClass, trip);
    }

    private int priceTrip(TravelClass travelClass, Trip trip) {
        return (trip.ticketPrice() * travelClass.coefficient()) + trip.agencyFees() + trip.travelFees();
    }

    private void checkDestination(final Destination destination) {
        if (destination == null)
            throw new IllegalArgumentException();
    }
}