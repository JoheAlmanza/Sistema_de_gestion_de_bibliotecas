package com.library.books.repository;

import com.library.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
