package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad Cliente que representa un registro en la base de datos.
 * Cada instancia de esta clase será una fila en la tabla "cliente".
 */
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // Identificador único del cliente (autoincremental)

    // Atributos del cliente
    private String nombre;
    private int edad;
    private String genero;
    private boolean intolerancia;
    private String detalleIntolerancia;

    /**
     * Constructor vacío obligatorio para JPA.
     * Spring y Hibernate lo necesitan para crear objetos automáticamente.
     */
    public Cliente() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isIntolerancia() {
        return intolerancia;
    }
    public void setIntolerancia(boolean intolerancia) {
        this.intolerancia = intolerancia;
    }

    public String getDetalleIntolerancia() {
        return detalleIntolerancia;
    }
    public void setDetalleIntolerancia(String detalleIntolerancia) {
        this.detalleIntolerancia = detalleIntolerancia;
    }
}
