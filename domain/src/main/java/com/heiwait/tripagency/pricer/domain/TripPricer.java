package com.heiwait.tripagency.pricer.domain;

import com.heiwait.tripagency.pricer.domain.error.BusinessErrors;
import com.heiwait.tripagency.pricer.domain.error.BusinessException;

public class TripPricer {
    public Integer priceTrip(TravelClass travelClass, Trip trip) {
        if (Trip.Builder.MISSING_DESTINATION.equals(trip)) {
            throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return (trip.ticketPrice() * travelClass.coefficient()) + trip.agencyFees() + trip.stayFees();
    }
}