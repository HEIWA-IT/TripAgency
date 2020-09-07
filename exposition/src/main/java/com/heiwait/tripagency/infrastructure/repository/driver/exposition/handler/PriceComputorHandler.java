package com.heiwait.tripagency.infrastructure.repository.driver.exposition.handler;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelClass;
import com.heiwait.tripagency.domain.TripPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class PriceComputorHandler extends TripPricer {

    private final PriceComputorRepositoryManager priceComputorRepositoryManager;

    public PriceComputorHandler(final PriceComputorRepositoryManager priceComputorRepositoryManager){
        this.priceComputorRepositoryManager = priceComputorRepositoryManager;
    }

    private void setTripRepository(TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Integer priceTravel(final Destination destination, final TravelClass travelClass, final RepositoryType repositoryType) {
        TripRepositoryPort tripRepositoryAdapter = priceComputorRepositoryManager.getTripRepositoryAdapter(repositoryType);
        this.setTripRepository(tripRepositoryAdapter);
        return super.priceTrip(destination, travelClass);
    }
}
