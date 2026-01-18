package com.spring.angular.example.Bookservice;

import com.spring.angular.example.Bookservice.controller.BookController;
import com.spring.angular.example.Bookservice.entity.Book;
import com.spring.angular.example.Bookservice.repository.BookRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetBooks() throws Exception {
        Book book1 = new Book(1, "Java", 999);
        Book book2 = new Book(2, "Spring", 1199);
        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(get("/findAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].name").value("Spring"));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1, "Java", 999);

        when(bookRepository.save(book)).thenReturn(book);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(post("/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Java\",\"price\":999}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Java"));
    }
}
