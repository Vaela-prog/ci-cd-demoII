package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio JPA para la entidad Review.
// CRUD completo sin escribir SQL.
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
