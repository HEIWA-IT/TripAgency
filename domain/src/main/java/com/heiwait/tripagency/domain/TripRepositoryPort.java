package com.heiwait.tripagency.domain;

public interface TripRepositoryPort {
    Trip findTripByDestination(final Destination destination);
}
