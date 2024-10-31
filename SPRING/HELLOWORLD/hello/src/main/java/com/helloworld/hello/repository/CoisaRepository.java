package com.helloworld.hello.repository;

import com.helloworld.hello.model.Coisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CoisaRepository extends JpaRepository<Coisa, Long> {
    ArrayList<Coisa> findAll();
}
