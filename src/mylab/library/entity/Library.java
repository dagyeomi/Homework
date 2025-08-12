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

    /** ���� �߰�: ISBN �ߺ��̸� false */
    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null) return false;
        if (findBookByISBN(book.getIsbn()) != null) return false; // �ߺ� ����
        return books.add(book);
    }

    /** �������� ���� ���� �˻� (ù ��Ī ��ȯ) */
    public Book findBookByTitle(String title) {
        if (title == null) return null;
        for (Book b : books) {
            if (title.equalsIgnoreCase(b.getTitle())) return b;
        }
        return null;
    }

    /** ���ڷ� ���� ��� �˻� */
    public List<Book> findBooksByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (author.equalsIgnoreCase(b.getAuthor())) result.add(b);
        }
        return result;
    }

    /** ISBN���� ���� ���� �˻� */
    public Book findBookByISBN(String isbn) {
        if (isbn == null) return null;
        for (Book b : books) {
            if (isbn.equalsIgnoreCase(b.getIsbn())) return b;
        }
        return null;
    }

    /** ���� */
    public boolean checkOutBook(String isbn) {
        Book target = findBookByISBN(isbn);
        if (target == null) return false;
        return target.checkOut();
    }

    /** �ݳ� */
    public boolean returnBook(String isbn) {
        Book target = findBookByISBN(isbn);
        if (target == null) return false;
        target.returnBook();
        return true;
    }

    /** ���� ������ ���� ��� */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) result.add(b);
        }
        return result;
    }

    /** ��ü ���� ��� (����� ����) */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /** ��ü �Ǽ� */
    public int getTotalBooks() {
        return books.size();
    }

    /** ���� ���� �Ǽ� */
    public int getAvailableBooksCount() {
        return getAvailableBooks().size();
    }

    /** ���� �� �Ǽ� (����) */
    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}
