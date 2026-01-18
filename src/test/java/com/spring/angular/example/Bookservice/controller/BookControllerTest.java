package com.spring.angular.example.Bookservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        // Assuming Book has appropriate setters/getters
        // book.setName("Test Book");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBooks() throws Exception {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(get("/findAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(books.size()));
    }

    @Test
    public void testDeleteBook() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("book deleted with id : " + id));

        verify(bookRepository, times(1)).deleteById(id);
    }
}