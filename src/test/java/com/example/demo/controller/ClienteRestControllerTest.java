package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.ActiveProfiles;


import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteService clienteService;

    @Test
    void testGetClienteById() throws Exception {

        // Crear cliente de prueba
        Cliente c = new Cliente();
        c.setNombre("Eva");
        c.setEdad(25);
        c.setGenero("F");
        c.setIntolerancia(false);

        // Guardar y recuperar ID
        c = clienteService.guardar(c);

        // GET /api/clientes/{id}
        mockMvc.perform(get("/api/clientes/" + c.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Eva"))
                .andExpect(jsonPath("$.edad").value(25))
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }

    @Test
    void testCrearCliente() throws Exception {

        // JSON válido (sin coma final)
        String json = """
            {
                "nombre": "Lucia",
                "edad": 30,
                "genero": "F",
                "intolerancia": false
            }
            """;

        mockMvc.perform(post("/api/clientes")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void testEliminarCliente() throws Exception {

        // Crear cliente de prueba
        Cliente c = new Cliente();
        c.setNombre("Borrar");
        c.setEdad(40);
        c.setGenero("M");
        c.setIntolerancia(false);

        // Guardar y recuperar ID
        c = clienteService.guardar(c);

        // DELETE /api/clientes/{id}
        mockMvc.perform(delete("/api/clientes/" + c.getId()))
                .andExpect(status().isNoContent());
    }
}
