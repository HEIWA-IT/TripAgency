package com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.springdata;

import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Destination;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.Trip;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.TripRepositoryPort;
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