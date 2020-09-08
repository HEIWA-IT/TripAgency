package com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler;

import com.heiwait.tripagency.domain.TripPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorHandler extends TripPricer {
    public void setTripRepository(TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }
}
