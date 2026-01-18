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
 * @author padma
 *
 */
@RestController
public class BookController {

	/**
	 * 
	 */
	public BookController() {
		// TODO Auto-generated constructor stub
	}
	@CrossOrigin("http://localhost:4200")
    @GetMapping("/findAllBooks/{pathid}")
	public List<Book> getBooks(@RequestParam("name") String name, @RequestParam("Id") int id, @RequestParam("price") int price,@PathVariable ("pathid") int age){
		return Stream.of(new Book(id+age, name, price), new Book(101,"Java",999),
				new Book(102, "Spring",1199),new Book(103,"Hibernate",445),new Book(104,"Angular",888)).collect(Collectors.toList());
	}

}
