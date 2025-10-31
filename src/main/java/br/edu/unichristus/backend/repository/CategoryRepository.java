package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
