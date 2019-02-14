package com.heiwait.tripagency.infrastructure.application.handler;

import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorRepositoryManager {

    private TripRepositoryPort tripRepositoryMockAdapter;

    private TripRepositoryPort tripRepositoryJdbcTemplateAdapter;

    private TripRepositoryPort tripRepositoryJpaAdapter;

    public PriceComputorRepositoryManager(
            @Qualifier("TripRepositoryMockAdapter") final TripRepositoryPort tripRepositoryMockAdapter,
            @Qualifier("TripRepositoryJdbcTemplateAdapter") final TripRepositoryPort tripRepositoryJdbcTemplateAdapter,
            @Qualifier("TripRepositoryJpaAdapter") final TripRepositoryPort tripRepositoryJpaAdapter) {
        this.tripRepositoryMockAdapter = tripRepositoryMockAdapter;
        this.tripRepositoryJdbcTemplateAdapter = tripRepositoryJdbcTemplateAdapter;
        this.tripRepositoryJpaAdapter = tripRepositoryJpaAdapter;
    }

    TripRepositoryPort getTripRepositoryAdapter(RepositoryType repositoryType) {
        switch (repositoryType) {
            case MOCK:
               return tripRepositoryMockAdapter;
            case JDBC_TEMPLATE:
                return tripRepositoryJdbcTemplateAdapter;
            case JPA:
                return tripRepositoryJpaAdapter;
            default:
                return tripRepositoryMockAdapter;
        }
    }
}
