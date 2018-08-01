package com.heiwait.tripagency.infrastructure.application.handler;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.TravelPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.stereotype.Service;


@Service
public class PriceComputorHandler extends TravelPricer {

    private PriceComputorRepositoryManager priceComputorRepositoryManager;

    public PriceComputorHandler(final PriceComputorRepositoryManager priceComputorRepositoryManager){
        this.priceComputorRepositoryManager = priceComputorRepositoryManager;
    }

    private void setTripRepository(TripRepositoryPort tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Integer computeTravelPrice(final Destination destination, final RepositoryType repositoryType) {
        TripRepositoryPort tripRepositoryAdapter = priceComputorRepositoryManager.getTripRepositoryAdapter(repositoryType);
        this.setTripRepository(tripRepositoryAdapter);
        return super.computeTravelPrice(destination);
    }
}
