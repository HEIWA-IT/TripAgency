package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.heiwait.tripagency"})
@EnableJpaRepositories("com.heiwait.tripagency.infrastructure.repository.springdata")
@EntityScan("com.heiwait.tripagency.infrastructure.repository.springdata")
public class TripAgencyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripAgencyApplication.class, args);
	}
}
