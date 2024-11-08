package com.todo.list.repositories;

import com.todo.list.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    void deleteByMarcadoTrue();
}
