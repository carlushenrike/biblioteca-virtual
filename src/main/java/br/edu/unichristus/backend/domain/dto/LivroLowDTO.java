package br.edu.unichristus.backend.domain.dto;

import lombok.Data;

@Data
public class LivroLowDTO {

    private Long id;
    private String titulo;

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
}