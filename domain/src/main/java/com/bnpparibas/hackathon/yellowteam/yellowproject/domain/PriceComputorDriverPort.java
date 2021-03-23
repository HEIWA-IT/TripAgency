package com.bnpparibas.hackathon.yellowteam.yellowproject.domain;

public interface PriceComputorDriverPort {
    Integer priceTrip(final Destination destination, final TravelClass travelClass);
}
