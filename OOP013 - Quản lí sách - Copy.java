import java.util.*;

class Book {
    private String id, title, author;
    public Book(String id, String title, String author) {
        this.id = id; this.title = title; this.author = author;
    }
    public void display() {
        System.out.println("Sách: " + title + " (Mã: " + id + ") - " + author);
    }
}

class Borrow {
    private String id, name; private Book book; private int days;
    public Borrow(String id, Book book, String name, int days) {
        this.id = id; this.book = book; this.name = name; this.days = days;
    }
    public double fee() { return days * 5000.0; }
    public void display() {
        System.out.println("--- Phiếu mượn sách ---");
        System.out.println("Mã phiếu: " + id);
        System.out.println("Người mượn: " + name);
        book.display();
        System.out.println("Số ngày mượn: " + days);
        System.out.printf("Phí mượn: %.1f\n", fee());
    }
}

public class Main {
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        Book b = new Book(sc.nextLine(), sc.nextLine(), sc.nextLine());
        Borrow br = new Borrow(sc.nextLine(), b, sc.nextLine(), Integer.parseInt(sc.nextLine()));
        br.display();
        sc.close();
    }
}
