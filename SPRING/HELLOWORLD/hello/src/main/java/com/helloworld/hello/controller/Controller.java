package com.helloworld.hello.controller;

import com.helloworld.hello.model.Coisa;
import com.helloworld.hello.repository.CoisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    @Autowired
    private CoisaRepository repository;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll() {
        ArrayList<Coisa> coisas = this.repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(coisas);
    }
}
