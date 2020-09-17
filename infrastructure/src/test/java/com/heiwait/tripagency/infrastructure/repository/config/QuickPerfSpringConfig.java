package com.heiwait.tripagency.infrastructure.repository.config;

import org.quickperf.spring.sql.QuickPerfProxyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuickPerfSpringConfig {

    @Bean
    public QuickPerfProxyBeanPostProcessor dataSourceBeanPostProcessor() {
        return new QuickPerfProxyBeanPostProcessor();
    }

}
