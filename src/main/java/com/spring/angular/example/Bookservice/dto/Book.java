package com.spring.angular.example.Bookservice.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Book(int id, String name, int price) {
		this.id=id;
		this.name= name;
		this.price=price;
	}
	private int id;
	private String name;
	private int price;

}
