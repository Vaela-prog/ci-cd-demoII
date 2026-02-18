package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Review;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    // Servicio que gestiona las operaciones de reviews
    @Autowired
    private ReviewService reviewService;

    // Servicio que permite obtener los clientes
    @Autowired
    private ClienteService clienteService;

    // Mostrar listado de reviews en /reviews
    @GetMapping
    public String listarReviews(Model model) {
        // Obtenemos todas las reviews.
        List<Review> reviews = reviewService.listarTodas();
        // Las añadimos al modelo para que Thymeleaf las pinte.
        model.addAttribute("reviews", reviews);
        // Devolvemos el nombre de la plantilla HTML.
        return "reviews-list";
    }

    // Mostrar formulario para añadir review de un cliente.
    @GetMapping("/nuevo")
    public String nuevaReview(Model model) {
        // Creamos un objeto Review vacío para el formulario.
        Review review = new Review();
        // Obtenemos la lista de clientes para poder elegir uno en el formulario.
        List<Cliente> clientes = clienteService.listarTodos();

        // Añadimos al modelo la review y la lista de clientes.
        model.addAttribute("review", review);
        model.addAttribute("clientes", clientes);

        // Devolvemos la plantilla del formulario.
        return "reviews-form";
    }

    // Guardar una review nueva o editada
    @PostMapping("/guardar")
    public String guardarReview(@ModelAttribute Review review) {
        // Guardamos la review en la base de datos.
        reviewService.guardar(review);
        // Redirigimos al listado de reviews.
        return "redirect:/reviews";
    }

    // Cargar datos de una review para editarlos
    @GetMapping("/editar/{id}")
    public String editarReview(@PathVariable Long id, Model model) {

        // Obtenemos la review por ID
        Review review = reviewService.buscarPorId(id);
        // Obtenemos la lista de clientes para el desplegable.
        List<Cliente> clientes = clienteService.listarTodos();

        // Añadimos al modelo la review y los clientes.
        model.addAttribute("review", review);
        model.addAttribute("clientes", clientes);

        // Reutilizamos la misma plantilla del formulario.
        return "reviews-form";
    }

    // Eliminar una review por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarReview(@PathVariable Long id) {
        // Eliminamos la review por ID.
        reviewService.eliminar(id);
        // Redirigimos al listado.
        return "redirect:/reviews";
    }
}
