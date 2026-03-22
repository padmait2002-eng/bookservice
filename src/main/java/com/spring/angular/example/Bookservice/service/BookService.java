package com.spring.angular.example.Bookservice.service;

import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Book updateBook(Book book) {
        // validate input
        if (book == null || book.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book id is required for update");
        }

        return bookRepository.findById(book.getId()).map(existingBook -> {
            // update only provided fields
            if (book.getName() != null) {
                existingBook.setName(book.getName());
            }
            existingBook.setPrice(book.getPrice());
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + book.getId()));
    }
}
