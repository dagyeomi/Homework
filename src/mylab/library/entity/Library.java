package mylab.library.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Library {

    private final List<Book> books = new ArrayList<>();
    private String name;

    public Library(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /** 도서 추가: ISBN 중복이면 false */
    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null) return false;
        if (findBookByISBN(book.getIsbn()) != null) return false; // 중복 방지
        return books.add(book);
    }

    /** 제목으로 단일 도서 검색 (첫 매칭 반환) */
    public Book findBookByTitle(String title) {
        if (title == null) return null;
        for (Book b : books) {
            if (title.equalsIgnoreCase(b.getTitle())) return b;
        }
        return null;
    }

    /** 저자로 도서 목록 검색 */
    public List<Book> findBooksByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (author.equalsIgnoreCase(b.getAuthor())) result.add(b);
        }
        return result;
    }

    /** ISBN으로 단일 도서 검색 */
    public Book findBookByISBN(String isbn) {
        if (isbn == null) return null;
        for (Book b : books) {
            if (isbn.equalsIgnoreCase(b.getIsbn())) return b;
        }
        return null;
    }

    /** 대출 */
    public boolean checkOutBook(String isbn) {
        Book target = findBookByISBN(isbn);
        if (target == null) return false;
        return target.checkOut();
    }

    /** 반납 */
    public boolean returnBook(String isbn) {
        Book target = findBookByISBN(isbn);
        if (target == null) return false;
        target.returnBook();
        return true;
    }

    /** 대출 가능한 도서 목록 */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) result.add(b);
        }
        return result;
    }

    /** 전체 도서 목록 (방어적 복사) */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /** 전체 권수 */
    public int getTotalBooks() {
        return books.size();
    }

    /** 대출 가능 권수 */
    public int getAvailableBooksCount() {
        return getAvailableBooks().size();
    }

    /** 대출 중 권수 (통계용) */
    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}
