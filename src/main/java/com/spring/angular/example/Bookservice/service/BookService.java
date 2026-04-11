package com.spring.angular.example.Bookservice.service;
import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
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
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }
    public String deleteBook(int id) {
        bookRepository.deleteById(id);
        return "book deleted with id : " + id;
    }
    public Book updateBook(int id, Book book) {
        if (book == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book id is required for update");
        }
        return bookRepository.findById(id).map(existingBook -> {
            if (book.getName() != null && !book.getName().isEmpty()) {
                existingBook.setName(book.getName());
            }
            if (book.getPrice() > 0) {
                existingBook.setPrice(book.getPrice());
            }
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
    }
}
