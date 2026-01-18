/**
 * 
 */
package com.spring.angular.example.Bookservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.angular.example.Bookservice.dto.Book;

import jakarta.websocket.server.PathParam;

/**
 * Controller for handling book-related requests.
 * @author padma
 *
 */
@RestController
public class BookController {

	/**
	 * Default constructor.
	 */
	public BookController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves a list of books.
	 * This endpoint is accessible from http://localhost:4200.
	 *
	 * @param name The name of the book to be added.
	 * @param id The ID of the book to be added.
	 * @param price The price of the book to be added.
	 * @param age A path variable.
	 * @return A list of books, including a new book created from the request parameters.
	 */
	@CrossOrigin("http://localhost:4200")
    @GetMapping("/findAllBooks/{pathid}")
	public List<Book> getBooks(@RequestParam("name") String name, @RequestParam("Id") int id, @RequestParam("price") int price,@PathVariable ("pathid") int age){
		// Returns a stream of books, including a new book created from the request parameters, and collects them into a list.
		return Stream.of(new Book(id+age, name, price), new Book(101,"Java",999),
				new Book(102, "Spring",1199),new Book(103,"Hibernate",445),new Book(104,"Angular",888)).collect(Collectors.toList());
	}

}
