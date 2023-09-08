package com.oliveira.erudio.repositories;

import com.oliveira.erudio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}