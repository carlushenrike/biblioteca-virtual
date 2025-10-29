package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}