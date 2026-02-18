package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes") // Ruta base para todas las operaciones de clientes
public class ClienteController {

    // Servicio que gestiona todas las operaciones con los clientes
    @Autowired
    private ClienteService clienteService;

    // Método que muestra el listado completo de clientes.
    // Model permite enviar datos a la vista.
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos()); // cuando el usuario accede a /clientes
        return "clientes-list";
    }

    // Método que muestra el formulario para crear un nuevo cliente.
    @GetMapping("/nuevo") // se ejecuta cuando el usuario accede a /clientes/nuevo.
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes-form";
    }

    // Método que guarda un cliente nuevo o editado.
    // Se ejecuta al enviar el formulario (POST).
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    // Método que carga los datos de un cliente para editarlos.
    // Recibe el id del cliente desde la URL.
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes-form";
    }

    // Método que elimina un cliente según su id.
    @GetMapping("/eliminar/{id}") // al acceder a /clientes/eliminar/{id}.
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}
