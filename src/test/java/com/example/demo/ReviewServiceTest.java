package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void testGuardarReview() {
        // Creamos una review falsa
        Review r = new Review();
        r.setValoracion(5);

        // Indicamos al mock que devuelva esa review al guardar
        when(reviewRepository.save(any(Review.class))).thenReturn(r);

        // Llamamos al servicio
        Review resultado = reviewService.guardar(new Review());

        // Comprobamos que la review devuelta tiene la valoración esperada
        assertEquals(5, resultado.getValoracion());

        // Verificamos que save() se llamó una vez
        verify(reviewRepository, times(1)).save(any());
    }
}

