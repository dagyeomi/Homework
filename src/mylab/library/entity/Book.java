package mylab.library.entity;


public class Book {

    private String title;
    private String author;
    private String isbn;
    private int publishYear;
    private boolean isAvailable;

    public Book() {
        this.isAvailable = true;
    }

    public Book(String title, String author, String isbn, int publishYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.isAvailable = true; 
    }

    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public boolean isAvailable() { return isAvailable; }

    /** 대출 처리: 가능하면 true, 아니면 false */
    public boolean checkOut() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    /** 반납 처리 */
    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s / %s / %d / %s",
                isbn, title, author, publishYear, (isAvailable ? "대출가능" : "대출중"));
    }
}
