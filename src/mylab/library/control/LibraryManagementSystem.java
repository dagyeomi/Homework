package mylab.library.control;

import java.util.List;

import mylab.library.entity.Book;
import mylab.library.entity.Library;


public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("�߾� ������");
        addSampleBooks(library);

        displayAvailableBooks(library);

        // �˻� �׽�Ʈ
        testFindBook(library);

        // ���� �׽�Ʈ
        testCheckOut(library);

        // �ݳ� �׽�Ʈ
        testReturn(library);

        // ���� ��Ȳ
        System.out.println("\n=== ���� ��ü ��� ===");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }
        System.out.printf("�� %d�� / ���Ⱑ�� %d�� / ������ %d��%n",
                library.getTotalBooks(),
                library.getAvailableBooksCount(),
                library.getBorrowedBooksCount());
    }

    private static void addSampleBooks(Library library) {
        library.addBook(new Book("�ڹ� ���α׷���", "ȫ�浿", "ISBN-001", 2022));
        library.addBook(new Book("���̽� �����ͺм�", "�̸���", "ISBN-002", 2021));
        library.addBook(new Book("�˰��� �Թ�", "������", "ISBN-003", 2020));
        library.addBook(new Book("��Ʈ��ũ ����", "ȫ�浿", "ISBN-004", 2019));
    }

    private static void testFindBook(Library library) {
        System.out.println("\n=== �˻� �׽�Ʈ ===");
        Book byTitle = library.findBookByTitle("�ڹ� ���α׷���");
        System.out.println("�������� �˻�: " + (byTitle != null ? byTitle : "����"));

        List<Book> byAuthor = library.findBooksByAuthor("ȫ�浿");
        System.out.println("���ڷ� �˻�(ȫ�浿): " + byAuthor.size() + "��");

        Book byIsbn = library.findBookByISBN("ISBN-003");
        System.out.println("ISBN���� �˻�: " + (byIsbn != null ? byIsbn : "����"));
    }

    private static void testCheckOut(Library library) {
        System.out.println("\n=== ���� �׽�Ʈ ===");
        boolean ok1 = library.checkOutBook("ISBN-001");
        System.out.println("ISBN-001 ����: " + (ok1 ? "����" : "����"));
        boolean ok2 = library.checkOutBook("ISBN-001");
        System.out.println("ISBN-001 �����(�̹� ������): " + (ok2 ? "����" : "����"));
        displayAvailableBooks(library);
    }

    private static void testReturn(Library library) {
        System.out.println("\n=== �ݳ� �׽�Ʈ ===");
        boolean ok = library.returnBook("ISBN-001");
        System.out.println("ISBN-001 �ݳ�: " + (ok ? "����" : "����"));
        displayAvailableBooks(library);
    }

    private static void displayAvailableBooks(Library library) {
        System.out.println("\n=== ���� ���� ���� ��� (" + library.getName() + ") ===");
        for (Book b : library.getAvailableBooks()) {
            System.out.println(b);
        }
        System.out.printf("���Ⱑ�� %d�� / ��ü %d��%n",
                library.getAvailableBooksCount(), library.getTotalBooks());
    }
}
