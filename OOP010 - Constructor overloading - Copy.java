public class Main {
    public static void main(String[] args) {
        System.out.println(new Product("Tape measure"));
        System.out.println(new Product("Plaster", "home improvement section"));
        System.out.println(new Product("Tyre", 5));
    }
}

class Product {
    String name;
    String location;
    int weight;

    // Constructor with only name
    Product(String name) {
        this(name, "shelf", 1);
    }

    // Constructor with name and location
    Product(String name, String location) {
        this(name, location, 1);
    }

    // Constructor with name and weight
    Product(String name, int weight) {
        this(name, "shelf", weight);
    }

    // Full constructor
    Product(String name, String location, int weight) {
        this.name = name;
        this.location = location;
        this.weight = weight;
    }

    public String toString() {
        return name + " (" + weight + "kg) can be found from the " + location;
    }
}
