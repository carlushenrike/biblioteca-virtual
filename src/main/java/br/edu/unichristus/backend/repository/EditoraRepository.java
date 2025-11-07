package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
