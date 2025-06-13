import java.util.*;
import java.util.regex.*;

interface PricedItem {
    double getPrice();
}

interface SourceableItem {
    String getSourceName();
}

class Dish implements PricedItem, SourceableItem {
    private String name, restaurant;
    private double price;

    public Dish(String name, double price, String restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getSourceName() {
        return restaurant;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Dish> list = new ArrayList<>();

        sc.nextLine(); // Bỏ dòng đầu tiên

        Pattern pattern = Pattern.compile("Dish\\s+\"(.*?)\"\\s+(\\d+(?:\\.\\d+)?)\\s+\"(.*?)\"");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("Checkout")) break;
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(1);
                double price = Double.parseDouble(matcher.group(2));
                String restaurant = matcher.group(3);
                list.add(new Dish(name, price, restaurant));
            }
        }

        String target = sc.nextLine().replaceAll("^\"|\"$", "");

        for (Dish dish : list) {
            if (dish.getSourceName().equals(target)) {
                System.out.printf("\"%s\" - %.2f\n", dish.getName(), dish.getPrice());
            }
        }
    }
}
