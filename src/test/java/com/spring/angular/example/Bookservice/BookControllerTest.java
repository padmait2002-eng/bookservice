package com.spring.angular.example.Bookservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.angular.example.Bookservice.controller.BookController;
import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBooks() throws Exception {
        Book book1 = new Book(1, "Java", 999);
        Book book2 = new Book(2, "Spring", 1199);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/findAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].name").value("Spring"));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1, "Java", 999);

        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        int bookId = 1;
        String expectedResponse = "Book removed with id " + bookId;
        when(bookService.deleteBook(bookId)).thenReturn(expectedResponse);

        mockMvc.perform(delete("/delete/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
