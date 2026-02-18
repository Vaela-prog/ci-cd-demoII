package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase pertenece a la capa de servicio 
public class ClienteService {

    // crea automáticamente un objeto ClienteRepository
  
    @Autowired
    private ClienteRepository clienteRepository;

    // Devuelve la lista completa de clientes almacenados en la base de datos.
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Busca un cliente por su ID. Si no existe, devuelve null.
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Guarda un cliente nuevo o actualiza uno existente.
    // Devuelve el cliente guardado (con ID si es nuevo).
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Elimina un cliente según su ID.
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
