package br.edu.unichristus.backend.domain.dto;

import lombok.Data;
import java.util.Set;

@Data
public class LivroDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private Integer numPaginas;
    private String sinopse;

    // ID1s para relacionamentos
    private Long categoriaId;
    private Long editoraId;
    private Set<Long> autorIds;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    public Set<Long> getAutorIds() {
        return autorIds;
    }

    public void setAutorIds(Set<Long> autorIds) {
        this.autorIds = autorIds;
    }
}