package com.rookies4.MySpringBoot3Homework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "books",
        indexes = {
                @Index(name = "idx_books_isbn", columnList = "isbn", unique = true),
                @Index(name = "idx_books_author", columnList = "author")
        })
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 200)
    private String title;

    @NotBlank @Size(max = 100)
    private String author;

    @NotBlank @Size(max = 20)
    @Column(unique = true)
    private String isbn;

    @PastOrPresent
    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Min(0)
    private Integer price;

    public Book() { }

    public Book(String title, String author, String isbn, LocalDate publishDate, Integer price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.price = price;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public LocalDate getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDate publishDate) { this.publishDate = publishDate; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book b)) return false;
        return Objects.equals(id, b.id) && Objects.equals(isbn, b.isbn);
    }
    @Override public int hashCode() { return Objects.hash(id, isbn); }
}
