package mylab.library.control;

import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;


public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("중앙 도서관");
        addSampleBooks(library);

        displayAvailableBooks(library);

        // 검색 테스트
        testFindBook(library);

        // 대출 테스트
        testCheckOut(library);

        // 반납 테스트
        testReturn(library);

        // 최종 현황
        System.out.println("\n=== 최종 전체 목록 ===");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }
        System.out.printf("총 %d권 / 대출가능 %d권 / 대출중 %d권%n",
                library.getTotalBooks(),
                library.getAvailableBooksCount(),
                library.getBorrowedBooksCount());
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "홍길동", "ISBN-001", 2022));
        library.addBook(new Book("파이썬 데이터분석", "이몽룡", "ISBN-002", 2021));
        library.addBook(new Book("알고리즘 입문", "성춘향", "ISBN-003", 2020));
        library.addBook(new Book("네트워크 기초", "홍길동", "ISBN-004", 2019));
    }

    private static void testFindBook(Library library) {
        System.out.println("\n=== 검색 테스트 ===");
        Book byTitle = library.findBookByTitle("자바 프로그래밍");
        System.out.println("제목으로 검색: " + (byTitle != null ? byTitle : "없음"));

        List<Book> byAuthor = library.findBooksByAuthor("홍길동");
        System.out.println("저자로 검색(홍길동): " + byAuthor.size() + "권");

        Book byIsbn = library.findBookByISBN("ISBN-003");
        System.out.println("ISBN으로 검색: " + (byIsbn != null ? byIsbn : "없음"));
    }

    private static void testCheckOut(Library library) {
        System.out.println("\n=== 대출 테스트 ===");
        boolean ok1 = library.checkOutBook("ISBN-001");
        System.out.println("ISBN-001 대출: " + (ok1 ? "성공" : "실패"));
        boolean ok2 = library.checkOutBook("ISBN-001");
        System.out.println("ISBN-001 재대출(이미 대출중): " + (ok2 ? "성공" : "실패"));
        displayAvailableBooks(library);
    }

    private static void testReturn(Library library) {
        System.out.println("\n=== 반납 테스트 ===");
        boolean ok = library.returnBook("ISBN-001");
        System.out.println("ISBN-001 반납: " + (ok ? "성공" : "실패"));
        displayAvailableBooks(library);
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("\n=== 대출 가능 도서 목록 (" + library.getName() + ") ===");
        for (Book b : library.getAvailableBooks()) {
            System.out.println(b);
        }
        System.out.printf("대출가능 %d권 / 전체 %d권%n",
                library.getAvailableBooksCount(), library.getTotalBooks());
    }
}
