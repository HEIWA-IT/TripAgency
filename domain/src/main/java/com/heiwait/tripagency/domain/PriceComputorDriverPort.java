package com.heiwait.tripagency.domain;

public interface PriceComputorDriverPort {
    Integer priceTrip(final Destination destination, final TravelClass travelClass);
}
