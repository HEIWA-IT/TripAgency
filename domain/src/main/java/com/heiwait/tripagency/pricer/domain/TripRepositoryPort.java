package com.bnpparibas.hackathon.yellowteam.yellowproject.domain;

public interface TripRepositoryPort {
    Trip findTripByDestination(final Destination destination);
}
