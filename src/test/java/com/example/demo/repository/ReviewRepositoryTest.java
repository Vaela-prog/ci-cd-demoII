package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Review;


@ActiveProfiles("test")

@DataJpaTest // Usa base de datos H2 para pruebas
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testGuardarYListar() {
        // Creamos dos reviews de prueba
        Review r1 = new Review();
        r1.setValoracion(5);

        Review r2 = new Review();
        r2.setValoracion(3);

        // Guardamos ambas en la base de datos 
        reviewRepository.save(r1);
        reviewRepository.save(r2);

        // Recuperamos todas las reviews
        List<Review> lista = reviewRepository.findAll();

        // Comprobamos que hay al menos 2
        assertTrue(lista.size() >= 2);
    }
}
