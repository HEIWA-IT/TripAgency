package com.heiwait.tripagency.domain;

public interface PriceComputorDriverPort {
    Integer computeTravelPrice(final Destination destination);

    // Warning because here we did not expresse a domain intent
    // See for a better design !!!!
    void setTripRepository(TripRepositoryPort tripRepository);
}
