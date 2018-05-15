package com.heiwait.tripagency.infrastructure.repository.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.heiwait.tripagency"})
@EnableJpaRepositories("com.heiwait.tripagency.infrastructure.repository.springdata")
@EntityScan("com.heiwait.tripagency.infrastructure.repository.springdata")
public class AppConfig {
}