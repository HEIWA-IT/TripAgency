package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.handler;

import com.heiwait.tripagency.domain.TravelPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorHandler extends TravelPricer {
    public PriceComputorHandler(
            @Qualifier("TripRepositoryJdbcTemplateAdapter") TripRepositoryPort tripRepositoryPort) {
        super(tripRepositoryPort);
    }
}
