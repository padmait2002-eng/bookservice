package com.spring.angular.example.Bookservice.controller;

import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling book-related requests.
 */
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Adds a new book to the database.
     *
     * @param book The book to add.
     * @return The saved book.
     */
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A list of all books.
     */
    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete.
     * @return A confirmation message.
     */
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "book deleted with id : " + id;
    }
}
