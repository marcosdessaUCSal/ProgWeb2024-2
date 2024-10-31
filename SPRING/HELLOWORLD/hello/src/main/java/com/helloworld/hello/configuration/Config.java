package com.helloworld.hello.configuration;

import com.helloworld.hello.model.Coisa;
import com.helloworld.hello.repository.CoisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Config implements CommandLineRunner {

    @Autowired
    private CoisaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Coisa c1 = new Coisa(null, "Marcos", "Dessa");
        Coisa c2 = new Coisa(null, "Fulano", "Beltrano");
        this.repository.saveAll(Arrays.asList(c1, c2));
    }
}
