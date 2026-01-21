package com.example.ejercicioClase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EjercicioClaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioClaseApplication.class, args);
	}

}
