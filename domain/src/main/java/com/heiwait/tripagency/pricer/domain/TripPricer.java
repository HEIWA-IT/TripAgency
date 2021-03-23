package com.bnpparibas.hackathon.yellowteam.yellowproject.domain;

import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.error.BusinessErrors;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.error.BusinessException;

public class TripPricer implements PriceComputorDriverPort {

    protected TripRepositoryPort tripRepository;

    public TripPricer(final TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Integer priceTrip(final Destination destination, final TravelClass travelClass) {
        checkDestination(destination);

        Trip trip = tripRepository.findTripByDestination(destination);

        return priceTrip(travelClass, trip);
    }

    private int priceTrip(TravelClass travelClass, Trip trip) {
        if (Trip.Builder.MISSING_DESTINATION.equals(trip)) {
            throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return (trip.ticketPrice() * travelClass.coefficient()) + trip.agencyFees() + trip.stayFees();
    }

    private void checkDestination(final Destination destination) {
        if (destination == null)
            throw new IllegalArgumentException();
    }
}