package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    final Trip parisTrip = new Trip(new Destination("Paris"), 25, 250, 150);
    final Trip lilleTrip = new Trip(new Destination("Lille"), 0, 0, 0);
    final Trip newyorkTrip = new Trip(new Destination("New-York"), 100, 2000, 1000);
    final Trip tokyoTrip = new Trip(new Destination("Tokyo"), 150, 3000, 1100);
    final Trip beijingTrip = new Trip(new Destination("Beijing"), 120, 1200, 1200);

    final Map<Destination, Trip> trips = Map.ofEntries(
            Map.entry(new Destination("Paris"), parisTrip),
            Map.entry(new Destination("Lille"), lilleTrip),
            Map.entry(new Destination("New-York"), newyorkTrip),
            Map.entry(new Destination("Tokyo"), tokyoTrip),
            Map.entry(new Destination("Beijing"), beijingTrip)
    );

    @Override
    public Trip findTripByDestination(final Destination destination) {
        Trip findedTrip = trips.get(destination);

        if (findedTrip != null) {
            return findedTrip;
        } else {
            throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }
    }
}
