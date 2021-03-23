package com.bnpparibas.hackathon.yellowteam.yellowproject.driver.exposition.handler;

import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.TripPricer;
import com.bnpparibas.hackathon.yellowteam.yellowproject.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TripPricerWithJdbcTemplateRepositoryAdapter extends TripPricer {
        public TripPricerWithJdbcTemplateRepositoryAdapter(@Qualifier("TripRepositoryJdbcTemplateAdapter") final TripRepositoryPort tripRepository) {
        super(tripRepository);
    }
}
