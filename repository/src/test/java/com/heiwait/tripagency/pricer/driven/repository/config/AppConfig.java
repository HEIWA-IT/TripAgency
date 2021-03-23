package com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bnpparibas.hackathon.yellowteam.yellowproject"})
@EnableJpaRepositories("com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.springdata")
@EntityScan("com.bnpparibas.hackathon.yellowteam.yellowproject.driven.repository.springdata")
public class AppConfig {
}