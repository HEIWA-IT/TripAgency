package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripBuilder;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    final Trip lilleTrip = new TripBuilder().withAgencyFees(0).withStayFees(0).withTicketPrice(0).build();
    final Trip parisTrip = new TripBuilder().withAgencyFees(50).withStayFees(300).withTicketPrice(200).build();
    final Trip beijingTrip = new TripBuilder().withAgencyFees(100).withStayFees(1000).withTicketPrice(1200).build();
    final Trip newyorkTrip = new TripBuilder().withAgencyFees(150).withStayFees(1500).withTicketPrice(1000).build();
    final Trip tokyoTrip = new TripBuilder().withAgencyFees(200).withStayFees(2000).withTicketPrice(1500).build();

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

        return Objects.requireNonNullElse(findedTrip, Trip.MISSING_DESTINATION);
    }
}