package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
