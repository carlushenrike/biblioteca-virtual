package br.edu.unichristus.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "API - Biblioteca Virtual Pública",
				version = "1.0",
				description = "API RESTful para o projeto da NP2 de Tópicos Especiais em Programação Web. " +
                        "Esta API gerencia os recursos de uma Biblioteca Virtual Pública.",
				contact = @Contact(
						name = "Carlos Maia, Lucas Pinho",
						email = "carlushenrike53@gmail.com",
						url = "https://github.com/carlushenrike/...")
		)
)
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
