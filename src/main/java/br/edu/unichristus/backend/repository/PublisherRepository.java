package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
