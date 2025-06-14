import java.util.*;

class Author {
    String name, email;
    char gender;

    Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        if (gender == 'f' || gender == 'm') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Gender must be 'f' or 'm'");
        }
    }

    @Override
    public String toString() {
        return "Author[name=" + name + ", email=" + email + ", gender=" + gender + "]";
    }
}

class Book {
    String name;
    Author author;
    double price;
    int qty = 0;

    Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    Book(String name, Author author, double price, int qty) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book[name=" + name + ", " + author + ", price=" + price + ", qty=" + qty + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());

        while (t-- > 0) {
            String objectType = scanner.nextLine();
            if (objectType.equals("Book")) {
                String bookName = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                int qty = Integer.parseInt(scanner.nextLine());

                String authorType = scanner.nextLine();
                if (authorType.equals("Author")) {
                    String authorName = scanner.nextLine();
                    String email = scanner.nextLine();
                    char gender = scanner.nextLine().charAt(0);

                    Author author = new Author(authorName, email, gender);
                    Book book = new Book(bookName, author, price, qty);
                    System.out.println(book);
                }
            }
        }

        scanner.close();
    }
}
