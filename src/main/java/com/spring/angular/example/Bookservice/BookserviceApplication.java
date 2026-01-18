package com.spring.angular.example.Bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Bookservice Spring Boot application.
 * This class is the entry point for the application.
 */
@SpringBootApplication
public class BookserviceApplication {

	/**
	 * The main method, which serves as the entry point for the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// This line starts the Spring Boot application.
		SpringApplication.run(BookserviceApplication.class, args);
	}

}
