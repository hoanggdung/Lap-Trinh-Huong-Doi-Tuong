import java.util.*;

abstract class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name; this.price = price; this.quantity = quantity;
    }

    abstract double calculateCost(int quantity);

    void displayDetails() {
        System.out.printf("Name: %s\nPrice: $%.1f\nAvailable Quantity: %d\n", name, price, quantity);
    }
}

class Book extends Product {
    String author;

    Book(String name, double price, int quantity, String author) {
        super(name, price, quantity); this.author = author;
    }

    double calculateCost(int quantity) { return price * quantity; }

    void displayDetails() {
        super.displayDetails();
        System.out.println("Author: " + author);
    }
}

class Electronics extends Product {
    String brand;

    Electronics(String name, double price, int quantity, String brand) {
        super(name, price, quantity); this.brand = brand;
    }

    double calculateCost(int quantity) { return price * quantity * 1.1; }

    void displayDetails() {
        super.displayDetails();
        System.out.println("Brand: " + brand);
    }
}

class User {
    String username;
    double totalSpent = 0;

    User(String username) { this.username = username; }

    void buyProduct(Product p, int qty) {
        if (qty <= p.quantity) {
            double cost = p.calculateCost(qty);
            p.quantity -= qty; totalSpent += cost;
            System.out.printf("User: %s bought %d %s for $%.1f\n", username, qty, p.name, cost);
        } else System.out.printf("Insufficient quantity of %s available.\n", p.name);
    }
}

public class Main {
    public static void main(String[] args) {
        new Scanner(System.in).nextLine(); // skip test case count

        Product laptop = new Electronics("laptop", 20, 10, "Dell");
        Product book = new Book("Harry Potter", 10, 12, "camnh");

        User u1 = new User("Alice"), u2 = new User("Bob"), u3 = new User("Charlie");

        u1.buyProduct(laptop, 3); u1.buyProduct(book, 10);
        u2.buyProduct(laptop, 1); u3.buyProduct(book, 5);

        System.out.println("====");

        User[] users = {u1, u2, u3};
        for (int i = 0; i < users.length; i++)
            for (int j = i + 1; j < users.length; j++)
                if (users[i].totalSpent < users[j].totalSpent) {
                    User tmp = users[i]; users[i] = users[j]; users[j] = tmp;
                }

        System.out.println("Users with Highest Total Spent:");
        for (int i = 0; i < users.length; i++)
            System.out.printf("%d. %s: $%.1f\n", i + 1, users[i].username, users[i].totalSpent);

        System.out.println("====");
        laptop.displayDetails(); System.out.println("---"); book.displayDetails();
    }
}
