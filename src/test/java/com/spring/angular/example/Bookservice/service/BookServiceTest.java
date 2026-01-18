package com.spring.angular.example.Bookservice.service;

import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testSaveBook() {
        Book book = new Book(1, "Java", 999);
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);
        assertEquals(book, savedBook);
    }

    @Test
    public void testGetBooks() {
        Book book1 = new Book(1, "Java", 999);
        Book book2 = new Book(2, "Spring", 1199);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getBooks();
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
    }

    @Test
    public void testDeleteBook() {
        int bookId = 1;
        String expectedResponse = "book deleted with id : " + bookId;

        doNothing().when(bookRepository).deleteById(bookId);

        String actualResponse = bookService.deleteBook(bookId);
        assertEquals(expectedResponse, actualResponse);
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
