package com.heiwait.tripagency.infrastructure.application.handler;

import com.heiwait.tripagency.domain.TravelPricer;
import org.springframework.stereotype.Service;


@Service
public class PriceComputorHandler extends TravelPricer {

    private PriceComputorRepositoryManager priceComputorRepositoryManager;

    public PriceComputorHandler(){}

    public PriceComputorHandler(final PriceComputorRepositoryManager priceComputorRepositoryManager){
        this.priceComputorRepositoryManager= priceComputorRepositoryManager;
    }
}
