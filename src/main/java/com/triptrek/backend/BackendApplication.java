package com.triptrek.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("========================================");
		System.out.println("🚀 TripTrek Backend Started!");
		System.out.println("📝 API: http://localhost:8080/api/enquiries");
		System.out.println("========================================");
	}
}