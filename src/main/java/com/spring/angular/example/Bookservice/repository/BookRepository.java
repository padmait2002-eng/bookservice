package com.spring.angular.example.Bookservice.repository;

import com.spring.angular.example.Bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Book entities.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
