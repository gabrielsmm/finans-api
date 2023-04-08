package com.gabrielsmm.financas.config;

import com.gabrielsmm.financas.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DbService dbService;

    @Bean
    void instanciaBaseDeDados() {
        try {
            dbService.instanciaBaseDeDados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
