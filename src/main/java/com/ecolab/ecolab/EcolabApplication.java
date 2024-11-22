package com.ecolab.ecolab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
		info = @Info(
				title = "EcoLab",
				summary = "API de controle de gastos com energia elétrica",
				description = "Uma Api voltada a auxiliar no controle e economia de gastos com energia elétrica.",
				version = "1.0.0",
				contact = @Contact(name = "Julia Nery", email = "sac@ecolab.com")
		)
)
public class EcolabApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcolabApplication.class, args);
	}

}
