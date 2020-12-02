package com.heiwait.tripagency.pricer.driven.repository.springdata;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.driven.repository.TripRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("TripRepositoryJpa")
public class TripRepositoryJpa implements TripRepository {

    private final TripJpaRepository tripJpaRepository;

    public TripRepositoryJpa(final TripJpaRepository tripJpaRepository) {
        this.tripJpaRepository = tripJpaRepository;
    }

    @Override
    public Trip findTripByDestination(Destination destination) {
        TripEntity tripEntity = tripJpaRepository.findTripByDestination(destination.name());

        Optional<TripEntity> tripEntityOptional = Optional.ofNullable(tripEntity);
        if (tripEntityOptional.isPresent())
            return tripEntityOptional.get().toTrip();
        else
            return Trip.Builder.MISSING_DESTINATION;
    }
}