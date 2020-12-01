package com.heiwait.tripagency.pricer.driver.exposition.handler;

import com.heiwait.tripagency.pricer.domain.TripPricer;
import com.heiwait.tripagency.pricer.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripPricerWithMockRepositoryAdapter extends TripPricer {
        public TripPricerWithMockRepositoryAdapter(@Qualifier("TripRepositoryMockAdapter") final TripRepositoryPort tripRepository) {
        super(tripRepository);
    }
}
