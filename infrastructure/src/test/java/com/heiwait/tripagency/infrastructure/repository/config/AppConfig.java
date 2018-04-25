package com.heiwait.tripagency.infrastructure.repository.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.heiwait.tripagency")
@SpringBootTest(classes = AppConfig.class)
public class AppConfig {
}