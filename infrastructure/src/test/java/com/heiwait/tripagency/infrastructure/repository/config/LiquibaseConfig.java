package com.heiwait.tripagency.infrastructure.repository.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class LiquibaseConfig {


    @Value("${spring.datasource.driverclassname}")
    private String driverClassName = "org.h2.Driver";

    @Value("${spring.datasource.url}")
    private String jdbcURl = "jdbc:h2:mem:my-trip-db";

    @Value("${spring.datasource.username}")
    private String username = "sa";

    @Value("${spring.datasource.password}")
    private String password ="";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getdataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(jdbcURl)
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .build();
    }

    //@Autowired
    //private DataSource dataSource;

    //@Value("${spring.liquibase.changelog}")
    private String changelog = "classpath:db/changelog/db.changelog-master.yaml";

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelog);
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
}