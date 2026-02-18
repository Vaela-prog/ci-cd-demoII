package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Review;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // Controlador encargado de generar los datos del informe
public class ReportController {

    // Servicios que proporcionan los datos de clientes y reviews
    private final ClienteService clienteService;
    private final ReviewService reviewService;

    // Constructor que recibe ambos servicios
    public ReportController(ClienteService clienteService, ReviewService reviewService) {
        this.clienteService = clienteService;
        this.reviewService = reviewService;
    }

    // Ruta principal del informe: /report
    @GetMapping("/report")
    public String reportPage(Model model) {


        // Gráfico 1: Clientes por género

        List<Cliente> clientes = clienteService.listarTodos();

        // Cuenta cuántos clientes tienen género Hombre (y filtra)
        long hombres = clientes.stream().filter(c -> "Hombre".equalsIgnoreCase(c.getGenero())).count();
        long mujeres = clientes.stream().filter(c -> "Mujer".equalsIgnoreCase(c.getGenero())).count();
        long otros   = clientes.stream().filter(c -> "Otro".equalsIgnoreCase(c.getGenero())).count();

        model.addAttribute("generos", List.of(hombres, mujeres, otros));


        // Gráfico 2: Reviews según nº de estrellas

        List<Review> reviews = reviewService.listarTodas();

        // Filtra las reviews con 1 estrella y devuelve cuántas cumplen esa condición
        long estrellas1 = reviews.stream().filter(r -> r.getValoracion() == 1).count();
        long estrellas2 = reviews.stream().filter(r -> r.getValoracion() == 2).count();
        long estrellas3 = reviews.stream().filter(r -> r.getValoracion() == 3).count();
        long estrellas4 = reviews.stream().filter(r -> r.getValoracion() == 4).count();
        long estrellas5 = reviews.stream().filter(r -> r.getValoracion() == 5).count();

        model.addAttribute("estrellas", List.of(estrellas1, estrellas2, estrellas3, estrellas4, estrellas5));


        // Gráfico 3: Clientes por franjas de edad
    
        // Cuenta cuántos clientes tienen entre 0 y 15 añicos
        long f0_15  = clientes.stream().filter(c -> c.getEdad() >= 0  && c.getEdad() <= 15).count();
        long f16_24 = clientes.stream().filter(c -> c.getEdad() >= 16 && c.getEdad() <= 24).count();
        long f25_35 = clientes.stream().filter(c -> c.getEdad() >= 25 && c.getEdad() <= 35).count();
        long f36_50 = clientes.stream().filter(c -> c.getEdad() >= 36 && c.getEdad() <= 50).count();
        long f51_65 = clientes.stream().filter(c -> c.getEdad() >= 51 && c.getEdad() <= 65).count();
        long f66    = clientes.stream().filter(c -> c.getEdad() >= 66).count();

        model.addAttribute("edades", List.of(f0_15, f16_24, f25_35, f36_50, f51_65, f66));


   
        // KPI libre: Clientes con intolerancia vs sin intolerancia
   
        /// Cuenta cuántos clientes tienen intolerancia
        long conIntolerancia  = clientes.stream().filter(Cliente::isIntolerancia).count();
        long sinIntolerancia  = clientes.stream().filter(c -> !c.isIntolerancia()).count();//y los que no

        // Envía los datos de intolerancias a la vista; TODOS.
        model.addAttribute("intolerancias", List.of(conIntolerancia, sinIntolerancia));


        // Devolvemos la vista report.html con todos los datos cargados
        return "report";
    }
}
