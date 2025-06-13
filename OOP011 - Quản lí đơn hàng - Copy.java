import java.util.*;

class Product {
    private String id, name; private double price;
    public Product(String id, String name, double price) {
        this.id = id; this.name = name; this.price = price;
    }
    public void display() {
        System.out.printf("Sản phẩm: %s (Mã: %s)\n", name, id);
        System.out.printf("Giá: %.1f\n", price);
    }
    public double getPrice() { return price; }
}

class Order {
    private String id; private Product p; private int qty;
    public Order(String id, Product p, int qty) {
        this.id = id; this.p = p; this.qty = qty;
    }
    public double total() { return qty * p.getPrice(); }
    public void display() {
        System.out.println("--- Thông tin đơn hàng ---");
        System.out.println("Đơn hàng: " + id);
        p.display();
        System.out.println("Số lượng: " + qty);
        System.out.printf("Tổng tiền: %.1f\n", total());
    }
}

public class Main {
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        Product p = new Product(sc.nextLine(), sc.nextLine(), Double.parseDouble(sc.nextLine()));
        Order o = new Order(sc.nextLine(), p, Integer.parseInt(sc.nextLine()));
        o.display();
        sc.close();
    }
}
