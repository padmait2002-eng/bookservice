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
        Book book = new Book();
        // Assuming Book has appropriate setters/getters
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        assertEquals(book, savedBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testGetBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteBook() {
        int id = 1;
        doNothing().when(bookRepository).deleteById(id);

        String result = bookService.deleteBook(id);

        assertEquals("book deleted with id : " + id, result);
        verify(bookRepository, times(1)).deleteById(id);
    }
}