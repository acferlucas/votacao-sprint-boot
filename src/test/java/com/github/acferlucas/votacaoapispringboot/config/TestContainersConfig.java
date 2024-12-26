package com.github.acferlucas.votacaoapispringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestContainersConfig {
    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("cooperativa")
                .withUsername("user")
                .withPassword("password");
        postgres.start();
        return postgres;
    }
}
