package com.spring.angular.example.Bookservice.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Book data transfer object.
 * The @Data annotation from Lombok automatically generates getters, setters, toString, equals, and hashCode methods.
 * The @AllArgsConstructor annotation generates a constructor with all fields.
 * The @NoArgsConstructor annotation generates a no-argument constructor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

	/**
	 * The unique identifier for the book.
	 */
	private int id;
	/**
	 * The name of the book.
	 */
	private String name;
	/**
	 * The price of the book.
	 */
	private int price;

	/**
	 * The serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	// Note: The explicit getters and setters below are redundant because the @Data annotation
	// from Lombok already generates them. They can be safely removed.

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
