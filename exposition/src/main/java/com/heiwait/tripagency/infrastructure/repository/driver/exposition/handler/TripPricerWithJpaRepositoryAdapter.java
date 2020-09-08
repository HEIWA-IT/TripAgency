package com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler;

import com.heiwait.tripagency.domain.TripPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripPricerWithJpaRepositoryAdapter extends TripPricer {
        public TripPricerWithJpaRepositoryAdapter(@Qualifier("TripRepositoryJpaAdapter") final TripRepositoryPort tripRepository) {
        super(tripRepository);
    }
}
