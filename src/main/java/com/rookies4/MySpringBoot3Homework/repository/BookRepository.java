package com.rookies4.MySpringBoot3Homework.repository;

import com.rookies4.MySpringBoot3Homework.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
}
