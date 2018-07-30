package com.heiwait.tripagency.infrastructure.application.handler;

import com.heiwait.tripagency.domain.TravelPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorHandler extends TravelPricer {
    public PriceComputorHandler(
            //@Qualifier("TripRepositoryMockAdapter")
            //@Qualifier("TripRepositoryJdbcTemplateAdapter")
            @Qualifier("TripRepositoryJpaAdapter")
                    final TripRepositoryPort tripRepositoryPort) {
        super(tripRepositoryPort);
    }
}
