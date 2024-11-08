package com.todo.list.model;

import java.io.Serializable;

public class TarefaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String txt;
    public int marcado; // 0 = false,  1 = true

    public TarefaDto() {
    }

    public TarefaDto(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.txt = tarefa.getTxt();
        this.marcado = tarefa.isMarcado() ? 1 : 0;
    }
}
