package com.gabrielsmm.financas.config;

import com.gabrielsmm.financas.services.DbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public DevConfig(DbService dbService) {
        this.dbService = dbService;
    }

    @Bean
    void instanciaBaseDeDados() {
        if (this.strategy.equals("create")) {
            this.dbService.instanciaBaseDeDados();
        }
    }
}
