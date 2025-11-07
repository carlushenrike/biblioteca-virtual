package br.edu.unichristus.backend.domain.dto;

import lombok.Data;

@Data
public class CategoriaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String area;
    private String cor_hex;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCor_hex() {
        return cor_hex;
    }

    public void setCor_hex(String cor_hex) {
        this.cor_hex = cor_hex;
    }
}