package com.todo.list.config;

import com.todo.list.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private DbService DbService;

    @Bean
    public boolean testeInicial() {
        this.DbService.populaDb();
        return true;
    }
}
