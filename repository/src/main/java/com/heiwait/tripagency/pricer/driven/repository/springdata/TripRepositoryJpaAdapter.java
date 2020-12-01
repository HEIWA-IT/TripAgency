package com.heiwait.tripagency.pricer.driven.repository.springdata;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import com.heiwait.tripagency.pricer.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("TripRepositoryJpaAdapter")
public class TripRepositoryJpaAdapter implements TripRepositoryPort {

    private final TripJpaRepository tripJpaRepository;

    public TripRepositoryJpaAdapter(final TripJpaRepository tripJpaRepository) {
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