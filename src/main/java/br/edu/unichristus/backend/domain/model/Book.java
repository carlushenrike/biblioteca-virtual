package br.edu.unichristus.backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(length = 13, unique = true) // ISBN-13
    private String isbn;

    @Column
    private Integer anoPublicacao;

    @Column
    private Integer numPaginas;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    // RELACIONAMENTOS --------

    @ManyToOne(fetch = FetchType.LAZY) // Muitos livros para uma categoria
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY) // Muitos livros para uma editora
    @JoinColumn(name = "editora_id", nullable = false)
    private Publisher publisher;

    @ManyToMany // Muitos livros para muitos autores
    @JoinTable(
            name = "tb_livro_autor", // tabela de junção
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Author> autores = new HashSet<>();


    // getters & setters
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

    public Category getCategoria() {
        return category;
    }

    public void setCategoria(Category category) {
        this.category = category;
    }

    public Publisher getEditora() {
        return publisher;
    }

    public void setEditora(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Author> getAutores() {
        return autores;
    }

    public void setAutores(Set<Author> autores) {
        this.autores = autores;
    }
}