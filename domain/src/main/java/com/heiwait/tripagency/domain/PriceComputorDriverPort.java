package com.heiwait.tripagency.domain;

public interface PriceComputorDriverPort {
    Integer computeTravelPrice(final Destination destination, final TravelClass travelClass);
}
