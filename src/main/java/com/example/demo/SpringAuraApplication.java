package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Clase principal de proyecto Spring Boot.
 * 
 * La anotación @SpringBootApplication indica que esta clase es el punto de inicio
 * de la aplicación. Activa la configuración automática de Spring,
 * componentes y permite ejecutar el proyecto como una aplicación independiente.
 */
@SpringBootApplication
public class SpringAuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuraApplication.class, args);//inicia servidor Tomcat
	}

}
