import java.util.*;

class Product {
    String name;
    int price, stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return name + " " + price + " " + stock;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] parts = sc.nextLine().split(" ");
            String name = parts[0];
            int price = Integer.parseInt(parts[1]);
            int stock = Integer.parseInt(parts[2]);
            products.add(new Product(name, price, stock));
        }

        String criterion = sc.nextLine();
        Comparator<Product> comparator;

        switch (criterion) {
            case "BY_NAME":
                comparator = Comparator.comparing(p -> p.name);
                break;

            case "BY_PRICE":
                comparator = Comparator.comparingInt((Product p) -> p.price)
                                       .thenComparing(p -> p.name);
                break;

            case "BY_STOCK":
                comparator = Comparator.comparingInt((Product p) -> p.stock)
                                       .reversed()
                                       .thenComparing(p -> p.name);
                break;

            default:
                comparator = Comparator.comparing(p -> p.name);
                break;
        }

        products.sort(comparator);

        for (Product p : products) {
            System.out.println(p);
        }

        sc.close();
    }
}
