package com.heiwait.tripagency.domain;

public interface PriceComputorDriverPort {
    Integer priceTravel(final Destination destination, final TravelClass travelClass);
}
