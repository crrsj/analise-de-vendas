package br.com.dados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Teste para desenvolvedor junior",
			version = "1.0",
			description = "API para testar habilidades de desenvolvedor junior.",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class DadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DadosApplication.class, args);
	}

}
