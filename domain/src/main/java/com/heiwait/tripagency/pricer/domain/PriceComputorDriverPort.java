package com.heiwait.tripagency.pricer.domain;

public interface PriceComputorDriverPort {
    Integer priceTrip(final Destination destination, final TravelClass travelClass);
}
