import java.text.DecimalFormat;
import java.util.Scanner;

abstract class Car {
    protected double price;
    protected int year;
    public static double maxSalePrice = 0.0;

    private static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("#,##0.00");

    public Car(double price, int year) {
        this.price = price;
        this.year = year;
    }

    public abstract double calculateSalePrice();

    @Override
    public String toString() {
        double currentSalePrice = this.calculateSalePrice();
        return "Price: " + PRICE_FORMATTER.format(currentSalePrice) + " VND | Year: " + this.year;
    }

    public static String getFormattedMaxSalePrice() {
        return PRICE_FORMATTER.format(maxSalePrice);
    }
}

class ClassicCar extends Car {
    private static final double REGISTRATION_TAX_MULTIPLIER = 1.12;
    private static final double PLATE_FEE = 20000000;

    public ClassicCar(double price, int year) {
        super(price, year);
    }

    @Override
    public double calculateSalePrice() {
        double salePrice = price * REGISTRATION_TAX_MULTIPLIER + PLATE_FEE;
        if (salePrice > maxSalePrice) maxSalePrice = salePrice;
        return salePrice;
    }
}

class SportCar extends Car {
    public SportCar(double price, int year) {
        super(price, year);
    }

    @Override
    public double calculateSalePrice() {
        double rate = (year > 2018) ? 0.8 : (year > 2010 ? 0.5 : 0.1);
        double salePrice = price * rate;
        if (salePrice > maxSalePrice) maxSalePrice = salePrice;
        return salePrice;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            String type = parts[0];
            double price = Double.parseDouble(parts[1]);
            int year = Integer.parseInt(parts[2]);

            Car car = null;
            if (type.equals("SC")) car = new SportCar(price, year);
            else if (type.equals("CC")) car = new ClassicCar(price, year);

            if (car != null) System.out.println(car);
        }

        System.out.println("Most Expensive: " + Car.getFormattedMaxSalePrice() + " VND");
        sc.close();
    }
}
