package com.todo.list.service;

import com.todo.list.model.Tarefa;
import com.todo.list.model.TarefaDto;
import com.todo.list.repositories.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private DbService dbService;

    public List<TarefaDto> findAll() {
        List<Tarefa> lista = tarefaRepository.findAll();
        List<TarefaDto> listaDto = lista.stream().map(
                tarefa -> new TarefaDto(tarefa)
        ).collect(Collectors.toList());
        return listaDto;
    }

    public void marcarTudo(boolean marcado) {
        List<Tarefa> lista = tarefaRepository.findAll();
        for (Tarefa t : lista) {
            t.setMarcado(marcado); // pode ser true ou false
        }
        try {
            tarefaRepository.saveAll(lista);
        } catch (Exception e) {
            // TODO: decidir o que fazer no caso de falha
        }
    }

    @Transactional
    public void removerMarcados() {
        tarefaRepository.deleteByMarcadoTrue();
    }

    public void inverterMarcado(Integer id) {
        Optional<Tarefa> opt = tarefaRepository.findById(id);
        if (opt.isPresent()) {
            Tarefa tarefa = opt.get();
            try {
                tarefa.inverterMarcado();
                tarefaRepository.save(tarefa);
            } catch (Exception e) {
                // TODO: o que fazer se algo deu errado
            }
        }
    }

    public void delete(Integer id) {
        Optional<Tarefa> opt = tarefaRepository.findById(id);
        if (opt.isPresent()) {
            Tarefa tarefa = opt.get();
            try {
                tarefaRepository.deleteById(id);
            } catch (Exception e) {
                // TODO: o que fazer se algo deu errado
            }
        }
    }

    @Transactional
    public void reset() {
        try {
            tarefaRepository.deleteAll();
            dbService.populaDb();
        } catch (Exception e) {
            // TODO: o que fazer se algo deu errado
        }
    }

    public void add(Tarefa tarefa) {
        try {
            tarefaRepository.save(tarefa);
        } catch (Exception e) {
            // TODO: o que fazer se algo deu errado
        }
    }

}
