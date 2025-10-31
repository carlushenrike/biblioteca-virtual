package br.edu.unichristus.backend.repository;

import br.edu.unichristus.backend.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
