//GENERIC023 - Bộ Quản Lý Kho Đa Năng 
import java.util.*;
import java.util.function.*;

// Predicate interface
interface Predicate<T> {
    boolean test(T t);
}

// Function interface
interface Function<T, R> {
    R apply(T t);
}

class Warehouse<T> {
    private List<T> items;
    public Warehouse(List<T> items) {
        this.items = items;
    }

    public Optional<T> findById(String id, Function<T, String> idExtractor) {
        for (T item : items) {
            if (idExtractor.apply(item).equals(id)) return Optional.of(item);
        }
        return Optional.empty();
    }

    public List<T> filter(Predicate<T> predicate) {
        List<T> res = new ArrayList<>();
        for (T item : items) if (predicate.test(item)) res.add(item);
        return res;
    }

    public List<T> sort(Comparator<T> comparator) {
        List<T> res = new ArrayList<>(items);
        res.sort(comparator);
        return res;
    }

    public Optional<T> max(Comparator<T> comparator) {
        if (items.isEmpty()) return Optional.empty();
        T maxItem = items.get(0);
        for (T item : items) {
            if (comparator.compare(item, maxItem) > 0) maxItem = item;
        }
        return Optional.of(maxItem);
    }

    public Optional<T> min(Comparator<T> comparator) {
        if (items.isEmpty()) return Optional.empty();
        T minItem = items.get(0);
        for (T item : items) {
            if (comparator.compare(item, minItem) < 0) minItem = item;
        }
        return Optional.of(minItem);
    }
}

// Product class implementing Comparable<Product> by price ascending
class Product implements Comparable<Product> {
    protected String id;
    protected String name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id; this.name = name; this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.price);
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + "}";
    }
}

// Book class extends Product, add author
class Book extends Product {
    private String author;

    public Book(String id, String name, double price, String author) {
        super(id, name, price);
        this.author = author;
    }

    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "Book{id='" + id + "', name='" + name + "', price=" + price + ", author='" + author + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            List<Product> productList = new ArrayList<>();
            Warehouse<Product> warehouse = new Warehouse<>(productList);

            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;

                String[] parts = line.split(" ");
                String cmd = parts[0];

                switch (cmd) {
                    case "AddBook":
                        String id = parts[1];
                        String name = parts[2];
                        double price = Double.parseDouble(parts[3]);
                        String author = parts[4];
                        productList.add(new Book(id, name, price, author));
                        break;

                    case "FindById":
                        String findId = parts[1];
                        Optional<Product> found = warehouse.findById(findId, Product::getId);
                        System.out.println(found.map(Product::toString).orElse("Not found"));
                        break;

                    case "FilterPriceAbove":
                        double threshold = Double.parseDouble(parts[1]);
                        List<Product> filtered = warehouse.filter(p -> p.getPrice() > threshold);
                        if (filtered.isEmpty()) System.out.println("Not found");
                        else filtered.forEach(System.out::println);
                        break;

                    case "SortByName":
                        List<Product> sortedByName = warehouse.sort(Comparator.comparing(Product::getName));
                        if (sortedByName.isEmpty()) System.out.println("Not found");
                        else sortedByName.forEach(System.out::println);
                        break;

                    case "MaxByPrice":
                        Optional<Product> maxPrice = warehouse.max(Comparator.comparingDouble(Product::getPrice));
                        System.out.println(maxPrice.map(Product::toString).orElse("Not found"));
                        break;

                    case "MinByPrice":
                        Optional<Product> minPrice = warehouse.min(Comparator.comparingDouble(Product::getPrice));
                        System.out.println(minPrice.map(Product::toString).orElse("Not found"));
                        break;

                    default:
                        // unknown command, ignore or print error
                        break;
                }
            }
        }
        sc.close();
    }
}


