package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
