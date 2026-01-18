package com.spring.angular.example.Bookservice.repository;

import com.spring.angular.example.Bookservice.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndFindAll() {
        Book book = new Book();
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1);
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        Book savedBook = bookRepository.save(book);

        // Assuming Book entity has a getId() method
        bookRepository.deleteById(savedBook.getId());
        
        assertThat(bookRepository.findById(savedBook.getId())).isEmpty();
    }
}