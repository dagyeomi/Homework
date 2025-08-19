package com.rookies4.MySpringBoot3Homework.controller;

import com.rookies4.MySpringBoot3Homework.entity.Book;
import com.rookies4.MySpringBoot3Homework.exception.BusinessException;
import com.rookies4.MySpringBoot3Homework.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** POST /api/books : 새 도서 등록 */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        Book saved = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /** GET /api/books : 모든 도서 조회 */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /** GET /api/books/{id} : Optional.map/orElse 사용 */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getUserById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** GET /api/books/isbn/{isbn} : BusinessException 사용 */
    @GetMapping("/isbn/{isbn}")
    public Book getUserByIsbn(@PathVariable String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> BusinessException.notFound("ISBN(" + isbn + ")의 도서를 찾을 수 없습니다."));
    }

    /** PUT /api/books/{id} : 도서 정보 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Valid Book req) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> BusinessException.notFound("ID(" + id + ")의 도서를 찾을 수 없습니다."));

        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setIsbn(req.getIsbn());
        book.setPublishDate(req.getPublishDate());
        book.setPrice(req.getPrice());

        return ResponseEntity.ok(bookRepository.save(book));
    }

    /** DELETE /api/books/{id} : 도서 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> BusinessException.notFound("ID(" + id + ")의 도서를 찾을 수 없습니다."));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }
}
