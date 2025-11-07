package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
