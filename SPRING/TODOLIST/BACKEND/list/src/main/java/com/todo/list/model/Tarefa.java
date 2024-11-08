package com.todo.list.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String txt;
    private boolean marcado;

    public Tarefa() {
    }

    public Tarefa(String txt, boolean marcado) {
        this.txt = txt;
        this.marcado = marcado;
    }

    public Tarefa(TarefaDto dto) {
        this.id = dto.id;
        this.txt = dto.txt;
        this.marcado = dto.equals("1");
    }

    public void inverterMarcado() {
        this.marcado = !this.marcado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
}
