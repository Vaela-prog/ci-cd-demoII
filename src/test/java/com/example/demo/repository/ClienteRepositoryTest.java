package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Cliente;



@DataJpaTest
@ActiveProfiles("test") //ojo: para usar H2
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testGuardarYBuscarPorId() {
        // Creamos un cliente de prueba
        Cliente c = new Cliente();
        c.setNombre("Eva");

        // Guardamos el cliente en la base de datos 
        Cliente guardado = clienteRepository.save(c);
        
	
        // Buscamos el cliente por su id
        Optional<Cliente> encontrado = clienteRepository.findById(guardado.getId());

        // Comprobamos que el cliente existe
        assertTrue(encontrado.isPresent());

        // Comprobamos que el nombre es el mismo
        assertEquals("Eva", encontrado.get().getNombre());
    }
}
