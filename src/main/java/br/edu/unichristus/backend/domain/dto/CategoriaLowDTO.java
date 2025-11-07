package br.edu.unichristus.backend.domain.dto;

import lombok.Data;

@Data
public class CategoriaLowDTO {

    private Long id;
    private String nome;

    // Getters e Setters explícitos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}