package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controlador principal de la aplicación
public class HomeController {

    // Método que redirige la ruta raíz hacia el listado de clientes
    @GetMapping("/")
    public String home() {
        return "redirect:/clientes";
    }
}
