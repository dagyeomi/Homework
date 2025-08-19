package com.rookies4.MySpringBoot3Homework.repository;

import com.rookies4.MySpringBoot3Homework.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book saved1, saved2;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        saved1 = bookRepository.save(
                new Book("스프링 부트 입문", "홍길동", "9788956746425",
                        LocalDate.of(2025, 5, 7), 30000));

        saved2 = bookRepository.save(
                new Book("JPA 프로그래밍", "박둘리", "9788956746432",
                        LocalDate.of(2025, 4, 30), 35000));
    }

    @Test @DisplayName("도서 등록 테스트")
    void testCreateBook() {
        Book b = new Book("테스트북", "홍길동", "1111",
                LocalDate.now(), 1000);
        Book saved = bookRepository.save(b);
        assertThat(saved.getId()).isNotNull();
        assertThat(bookRepository.count()).isEqualTo(3);
    }

    @Test @DisplayName("ISBN으로 도서 조회 테스트")
    void testFindByIsbn() {
        Optional<Book> opt = bookRepository.findByIsbn("9788956746425");
        assertThat(opt).isPresent();
        assertThat(opt.get().getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test @DisplayName("저자명으로 도서 목록 조회 테스트")
    void testFindByAuthor() {
        List<Book> list = bookRepository.findByAuthor("박둘리");
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getIsbn()).isEqualTo("9788956746432");
    }

    @Test @DisplayName("도서 정보 수정 테스트")
    void testUpdateBook() {
        Book target = bookRepository.findByIsbn("9788956746425").orElseThrow();
        target.setPrice(31000);
        target.setTitle("스프링 부트 입문(개정)");
        Book updated = bookRepository.save(target);

        assertThat(updated.getPrice()).isEqualTo(31000);
        assertThat(bookRepository.findById(saved1.getId()).get().getTitle())
                .contains("개정");
    }

    @Test @DisplayName("도서 삭제 테스트")
    void testDeleteBook() {
        bookRepository.deleteById(saved2.getId());
        assertThat(bookRepository.findById(saved2.getId())).isEmpty();
        assertThat(bookRepository.count()).isEqualTo(1);
    }
}
