package com.gastos.seguimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gastos.seguimiento"})
public class SeguimientoGastosApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeguimientoGastosApplication.class, args);
	}
}

