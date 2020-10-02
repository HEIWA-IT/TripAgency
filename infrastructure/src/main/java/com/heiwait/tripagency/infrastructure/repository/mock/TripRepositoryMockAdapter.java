package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    final Trip lilleTrip = new Trip(0, 0, 0);
    final Trip parisTrip = new Trip(50, 300, 200);
    final Trip beijingTrip = new Trip(100, 1000, 1200);
    final Trip newyorkTrip = new Trip(150, 1500, 1000);
    final Trip tokyoTrip = new Trip(200, 2000, 1500);

    final Map<String, Trip> trips = new HashMap<String, Trip>() {{
        put("paris", parisTrip);
        put("lille", lilleTrip);
        put("new-york", newyorkTrip);
        put("tokyo", tokyoTrip);
        put("beijing", beijingTrip);
    }};

    @Override
    public Trip findTripByDestination(final Destination destination) {
        Trip findedTrip = trips.get(destination.name().toLowerCase());

        if (findedTrip != null)
            return findedTrip;
        else
            return Trip.MISSING_DESTINATION;
    }
}