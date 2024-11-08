package com.todo.list.service;

import com.todo.list.model.Tarefa;
import com.todo.list.repositories.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbService {

    @Autowired
    private TarefaRepository repository;

    @Transactional
    public void populaDb() {
        Tarefa t1 = new Tarefa("Tomar banho", false);
        Tarefa t2 = new Tarefa("Escovar os dentes", false);
        Tarefa t3 = new Tarefa("Lavar os pratos", false);
        Tarefa t4 = new Tarefa("Varrer a sala", false);

        repository.saveAll(Arrays.asList(t1, t2, t3, t4));
    }
}
