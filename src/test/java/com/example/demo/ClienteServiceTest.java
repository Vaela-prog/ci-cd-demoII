package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@ExtendWith(MockitoExtension.class) //  Mockito para este test
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository; // Simula base de datos

    @InjectMocks
    private ClienteService clienteService; // Servicio que vamos a probar

    @Test
    void testListarTodos() {
        // Creamos una lista 'falsa' de clientes
        List<Cliente> lista = List.of(new Cliente(), new Cliente());

        // decimos a mock que devuelva esa lista cuando se llame a findAll()
        when(clienteRepository.findAll()).thenReturn(lista);

        // Llamamos al servicio
        List<Cliente> resultado = clienteService.listarTodos();

        // Comprobamos que devuelve lo esperado
        assertEquals(2, resultado.size());

        // Verificamos que el repositorio se llamó una vez
        verify(clienteRepository, times(1)).findAll();
    }
}
