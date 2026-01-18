package com.spring.angular.example.Bookservice.service;

import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public String deleteBook(int id) {
        bookRepository.deleteById(id);
        return "book deleted with id : " + id;
    }
}