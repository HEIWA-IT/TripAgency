package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    final Trip lilleTrip = new Trip.Builder().with(builder -> {
        builder.agencyFees = 0;
        builder.stayFees = 0;
        builder.ticketPrice = 0;
    }).build();

    final Trip parisTrip = new Trip.Builder().with(builder -> {
        builder.agencyFees = 50;
        builder.stayFees = 300;
        builder.ticketPrice = 200;
    }).build();

    final Trip beijingTrip = new Trip.Builder().with(builder -> {
        builder.agencyFees = 100;
        builder.stayFees = 1000;
        builder.ticketPrice = 1200;
    }).build();

    final Trip newyorkTrip = new Trip.Builder().with(builder -> {
        builder.agencyFees = 150;
        builder.stayFees = 1500;
        builder.ticketPrice = 1000;
    }).build();

    final Trip tokyoTrip = new Trip.Builder().with(builder -> {
        builder.agencyFees = 200;
        builder.stayFees = 2000;
        builder.ticketPrice = 1500;
    }).build();

    final Map<String, Trip> trips = Map.ofEntries(
            Map.entry("paris", parisTrip),
            Map.entry("lille", lilleTrip),
            Map.entry("new-york", newyorkTrip),
            Map.entry("tokyo", tokyoTrip),
            Map.entry("beijing", beijingTrip)
    );

    @Override
    public Trip findTripByDestination(final Destination destination) {
        Trip findedTrip = trips.get(destination.name().toLowerCase());

        return Objects.requireNonNullElse(findedTrip, Trip.Builder.MISSING_DESTINATION);
    }
}