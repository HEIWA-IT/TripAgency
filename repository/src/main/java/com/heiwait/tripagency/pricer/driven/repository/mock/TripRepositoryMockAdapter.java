package com.heiwait.tripagency.pricer.driven.repository.mock;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    final Trip lilleTrip = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(0);
        builder.setStayFees(0);
        builder.setAgencyFees(0);
    }).build();

    final Trip parisTrip = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(100);
        builder.setStayFees(800);
        builder.setAgencyFees(50);
    }).build();

    final Trip beijingTrip = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(1000);
        builder.setStayFees(1000);
        builder.setAgencyFees(100);
    }).build();

    final Trip newyorkTrip = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(800);
        builder.setStayFees(1000);
        builder.setAgencyFees(100);
    }).build();

    final Trip tokyoTrip = new Trip.Builder().with(builder -> {
        builder.setTicketPrice(1200);
        builder.setStayFees(1000);
        builder.setAgencyFees(100);
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