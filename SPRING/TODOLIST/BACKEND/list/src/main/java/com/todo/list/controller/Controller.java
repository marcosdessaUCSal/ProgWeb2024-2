package com.todo.list.controller;

import com.todo.list.model.Tarefa;
import com.todo.list.model.TarefaDto;
import com.todo.list.repositories.TarefaRepository;
import com.todo.list.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping(value = "/tarefas")
    public ResponseEntity<List<TarefaDto>> findAll() {
        List<TarefaDto> listaDto = tarefaService.findAll();
        return ResponseEntity.ok().body(listaDto);
    }

    @GetMapping(value = "/marcaTudo")
    public ResponseEntity<Object> marcarTudo() {
        tarefaService.marcarTudo(true);
        return ResponseEntity.ok().body("");
    }

    @GetMapping(value = "/desmarcaTudo")
    public ResponseEntity<Object> desmarcarTudo() {
        tarefaService.marcarTudo(false);
        return ResponseEntity.ok().body("");
    }

    @GetMapping(value = "/removeMarcados")
    public ResponseEntity<Object> removerMarcados() {
        tarefaService.removerMarcados();
        return ResponseEntity.ok().body("");
    }

    @PutMapping(value = "/inverterMarcado/{id}")
    public ResponseEntity<Object> marcarOuDesmarcar(@PathVariable("id") Integer id) {
        tarefaService.inverterMarcado(id);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        tarefaService.delete(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping(value = "/reset")
    public ResponseEntity<Object> reset() {
        tarefaService.reset();
        return ResponseEntity.ok().body("");
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> add(@RequestBody TarefaDto dto) {
        tarefaService.add(new Tarefa(dto));
        return ResponseEntity.ok().body("");
    }

}
