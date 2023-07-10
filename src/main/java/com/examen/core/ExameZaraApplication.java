package com.examen.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.examen.core")

public class ExameZaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExameZaraApplication.class, args);
	}

}
