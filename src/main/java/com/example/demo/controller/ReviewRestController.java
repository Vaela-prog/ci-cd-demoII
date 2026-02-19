package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/api/reviews") // Ruta base del CRUD REST
public class ReviewRestController {

    private final ReviewService reviewService;

    // Inyección por constructor (forma recomendada)
    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

  
    // GET /api/reviews  devuelve todas las reviews
    
    @GetMapping
    public List<Review> listar() {
        return reviewService.listarTodas();
    }


    // GET /api/reviews/{id}  devuelve una review por ID
   
    @GetMapping("/{id}")
    public ResponseEntity<Review> buscarPorId(@PathVariable Long id) {

        Review review = reviewService.buscarPorId(id);

        if (review == null) {
            // Si no existe, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Si existe, devolvemos 200 OK con la review
        return ResponseEntity.ok(review);
    }

   
    // POST /api/reviews  crea una nueva review
   
    @PostMapping
    public ResponseEntity<Review> crear(@RequestBody Review review) {

        // Guardamos la review recibida en el body
        Review guardada = reviewService.guardar(review);

        // Devolvemos 201 Created con la review creada
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

   
    // PUT /api/reviews/{id}  actualiza una review existente
    
    @PutMapping("/{id}")
    public ResponseEntity<Review> actualizar(@PathVariable Long id, @RequestBody Review reviewActualizada) {

        // Buscamos si la review existe en la BD
        Review existente = reviewService.buscarPorId(id);

        if (existente == null) {
            // Si no existe, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Actualizamos los campos con los valores recibidos
        existente.setDescripcion(reviewActualizada.getDescripcion());
        existente.setValoracion(reviewActualizada.getValoracion());
        existente.setCliente(reviewActualizada.getCliente());

        // Guardamos la review actualizada
        Review guardada = reviewService.guardar(existente);

        // Devolvemos 200 OK con la review actualizada
        return ResponseEntity.ok(guardada);
    }

    
    // DELETE /api/reviews/{id}  elimina una review por ID
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        // Eliminamos la review por ID
        reviewService.eliminar(id);

        // 204 No Content → eliminado correctamente
        return ResponseEntity.noContent().build();
    }
}
