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

    final Trip parisTrip = new Trip(new Destination("Paris"), 25, 250, 150);
    final Trip lilleTrip = new Trip(new Destination("Lille"), 0, 0, 0);
    final Trip newyorkTrip = new Trip(new Destination("New-York"), 100, 2000, 1000);
    final Trip tokyoTrip = new Trip(new Destination("Tokyo"), 150, 3000, 1100);
    final Trip beijingTrip = new Trip(new Destination("Beijing"), 120, 1200, 1200);

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
