package com.example.demo.entity;

import jakarta.persistence.*;


@Entity 
public class Review {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private int valoracion;

    // Relación MANY-TO-ONE con Cliente:
    // Muchas reviews pueden pertenecer a un mismo cliente,
    // pero cada review solo tiene un cliente asociado.
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nombre de la columna FK en la tabla review.
    private Cliente cliente;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

