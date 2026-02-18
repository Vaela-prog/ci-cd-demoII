package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/clientes") // Ruta base del CRUD REST
public class ClienteRestController {

    private final ClienteService clienteService;

    // Inyección por constructor (forma recomendada en Spring)
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ============================================================
    // GET /api/clientes → devuelve todos los clientes
    // ============================================================
    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    // ============================================================
    // GET /api/clientes/{id} → devuelve un cliente por su ID
    // ============================================================
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente == null) {
            // Si no existe, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Si existe, devolvemos 200 OK con el cliente
        return ResponseEntity.ok(cliente);
    }

    // ============================================================
    // POST /api/clientes → crea un nuevo cliente
    // ============================================================
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Cliente cliente) {

        // Guardamos el cliente recibido en el cuerpo de la petición
        clienteService.guardar(cliente);

        // Devolvemos 201 Created sin cuerpo
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // ============================================================
    // PUT /api/clientes/{id} → actualiza un cliente existente
    // ============================================================
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {

        // 1. Buscamos si el cliente existe en la BD
        Cliente existente = clienteService.buscarPorId(id);

        if (existente == null) {
            // Si no existe, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // 2. Actualizamos los campos del cliente existente
        //    con los valores recibidos en el JSON del body
        existente.setNombre(clienteActualizado.getNombre());
        existente.setEdad(clienteActualizado.getEdad());
        existente.setGenero(clienteActualizado.getGenero());
        existente.setIntolerancia(clienteActualizado.isIntolerancia());
        existente.setDetalleIntolerancia(clienteActualizado.getDetalleIntolerancia());

        // 3. Guardamos el cliente actualizado en la BD
        Cliente guardado = clienteService.guardar(existente);

        // 4. Devolvemos 200 OK con el cliente actualizado
        return ResponseEntity.ok(guardado);
    }

    // ============================================================
    // DELETE /api/clientes/{id} → elimina un cliente por ID
    // ============================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        clienteService.eliminar(id);

        // 204 No Content → eliminado correctamente
        return ResponseEntity.noContent().build();
    }
}
