package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest;

import com.heiwait.tripagency.domain.TravelPricer;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages = {"com.heiwait.tripagency"})
public class TripAgencyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripAgencyApplication.class, args);
	}
}
