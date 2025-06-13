import java.util.Scanner;
class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public void display() {
        System.out.println("Sách: " + title + " (Mã: " + bookId + ") - " + author);
    }
}
class Borrow {
    private String borrowId;
    private Book book;
    private String borrower;
    private int days;
    public Borrow(String borrowId, Book book, String borrower, int days) {
        this.borrowId = borrowId;
        this.book = book;
        this.borrower = borrower;
        this.days = days;
    }
    public double calculateFee() {
        return days * 5000.0;
    }
    public void display() {
        System.out.println("--- Phiếu mượn sách ---");
        System.out.println("Mã phiếu: " + borrowId);
        System.out.println("Người mượn: " + borrower);
        book.display();
        System.out.println("Số ngày mượn: " + days);
        System.out.printf("Phí mượn: %.1f\n", calculateFee());
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bookId = sc.nextLine();
        String title = sc.nextLine();
        String author = sc.nextLine();
        String borrowId = sc.nextLine();
        String borrower = sc.nextLine();
        int days = Integer.parseInt(sc.nextLine());
        Book book = new Book(bookId, title, author);
        Borrow borrow = new Borrow(borrowId, book, borrower, days);
        borrow.display();
        sc.close();
    }
}