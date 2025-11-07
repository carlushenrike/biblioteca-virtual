package br.edu.unichristus.backend.domain.dto;

import lombok.Data;

@Data
public class ComentarioLowDTO {

    private Long id;
    private String titulo;
    private Integer nota;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
