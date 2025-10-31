package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}