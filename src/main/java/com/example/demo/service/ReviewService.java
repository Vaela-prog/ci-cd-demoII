package com.example.demo.service;

import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service // Clase de la capa Service que gestiona las reviews
public class ReviewService {

	// Repositorio con el que se accede a los datos de las reviews
    private final ReviewRepository reviewRepository;

    // Constructor
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    //devuelve lista completa de reviews
    public List<Review> listarTodas() {
        return reviewRepository.findAll();
    }

    public Review buscarPorId(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Guarda una review nueva o actualizada.
    //public void guardar(Review review) {
        //reviewRepository.save(review);
    //}
    
    public Review guardar(Review review) {
        return reviewRepository.save(review);
    }



    public void eliminar(Long id) {
        reviewRepository.deleteById(id);
    }
}
