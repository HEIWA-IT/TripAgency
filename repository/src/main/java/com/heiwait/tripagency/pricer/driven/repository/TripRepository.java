package com.heiwait.tripagency.pricer.driven.repository;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;

public interface TripRepository {
    Trip findTripByDestination(final Destination destination);
}
