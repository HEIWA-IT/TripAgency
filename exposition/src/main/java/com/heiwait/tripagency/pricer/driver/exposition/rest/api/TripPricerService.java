package com.heiwait.tripagency.pricer.driver.exposition.rest.api;

import com.heiwait.tripagency.pricer.domain.TripPricer;
import com.heiwait.tripagency.pricer.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripPricerService extends TripPricer {
    private final TripRepositoryPort tripRepository;

    public TripPricerService(@Qualifier("TripRepositoryJpaAdapter") final TripRepositoryPort tripRepository) {
        super(tripRepository);
        this.tripRepository = tripRepository;
    }
}