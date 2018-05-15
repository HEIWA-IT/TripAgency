package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.heiwait.tripagency"})
public class TripAgencyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripAgencyApplication.class, args);
	}
}
