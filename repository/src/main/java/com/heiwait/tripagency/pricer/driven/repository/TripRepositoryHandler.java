package com.heiwait.tripagency.pricer.driven.repository;

import com.heiwait.tripagency.pricer.domain.Destination;
import com.heiwait.tripagency.pricer.domain.Trip;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TripRepositoryHandler {
    private final TripRepository tripRepositoryMock;
    private final TripRepository tripRepositoryJdbcTemplate;
    private final TripRepository tripRepositoryJpa;

    public TripRepositoryHandler(
            final @Qualifier("TripRepositoryMock") TripRepository tripRepositoryMock,
            final @Qualifier("TripRepositoryJdbcTemplate") TripRepository tripRepositoryJdbcTemplate,
            final @Qualifier("TripRepositoryJpa") TripRepository tripRepositoryJpa) {
        this.tripRepositoryMock = tripRepositoryMock;
        this.tripRepositoryJdbcTemplate = tripRepositoryJdbcTemplate;
        this.tripRepositoryJpa = tripRepositoryJpa;
    }

    public Trip findTripByDestination(Destination destination, TripRepositoryType tripRepositoryType) {
        TripRepository tripRepository;

        switch (tripRepositoryType) {
            case JDBC:
                tripRepository = tripRepositoryJdbcTemplate;
                break;
            case JPA:
                tripRepository = tripRepositoryJpa;
                break;
            case MOCK:
            default:
                tripRepository = tripRepositoryMock;
                break;
        }

        return tripRepository.findTripByDestination(destination);
    }
}
