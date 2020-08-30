package com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler;

import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorRepositoryManager {

    private final TripRepositoryPort tripRepositoryMockAdapter;

    private final TripRepositoryPort tripRepositoryJdbcTemplateAdapter;

    private final TripRepositoryPort tripRepositoryJpaAdapter;

    public PriceComputorRepositoryManager(
            @Qualifier("TripRepositoryMockAdapter") final TripRepositoryPort tripRepositoryMockAdapter,
            @Qualifier("TripRepositoryJdbcTemplateAdapter") final TripRepositoryPort tripRepositoryJdbcTemplateAdapter,
            @Qualifier("TripRepositoryJpaAdapter") final TripRepositoryPort tripRepositoryJpaAdapter) {
        this.tripRepositoryMockAdapter = tripRepositoryMockAdapter;
        this.tripRepositoryJdbcTemplateAdapter = tripRepositoryJdbcTemplateAdapter;
        this.tripRepositoryJpaAdapter = tripRepositoryJpaAdapter;
    }

    public TripRepositoryPort getTripRepositoryAdapter(final RepositoryType repositoryType) {
        switch (repositoryType) {
            case JDBC_TEMPLATE:
                return tripRepositoryJdbcTemplateAdapter;
            case JPA:
                return tripRepositoryJpaAdapter;
            case MOCK:
            default:
                return tripRepositoryMockAdapter;
        }
    }
}
