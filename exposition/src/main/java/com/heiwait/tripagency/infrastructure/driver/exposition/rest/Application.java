package com.heiwait.tripagency.infrastructure.driver.exposition.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.heiwait.tripagency"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}