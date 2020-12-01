package com.heiwait.tripagency.pricer.domain;

public interface TripRepositoryPort {
    Trip findTripByDestination(final Destination destination);
}
