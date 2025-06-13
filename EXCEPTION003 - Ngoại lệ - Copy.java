import java.util.*;

class UsedCarException extends Exception {
    public UsedCarException(String vin) {
        super("Invalid UsedCar: VIN " + vin);
    }
}
class UsedCar {
    String vin, make;
    int year, mileage;
    double price;

    public UsedCar(String vin, String make, int year, int mileage, double price) throws UsedCarException {
        if (!vin.matches("\\d{4}") || 
            !Arrays.asList("Ford", "Honda", "Toyota", "Chrysler").contains(make) ||
            year < 1990 || year > 2014 || mileage < 0 || price < 0)
            throw new UsedCarException(vin);

        this.vin = vin; this.make = make;
        this.year = year; this.mileage = mileage; this.price = price;
    }

    public String toString() {
        return String.format("UsedCar{vin='%s', make='%s', year=%d, mileage=%d, price=%.1f}", 
                              vin, make, year, mileage, price);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<UsedCar> cars = new ArrayList<>();

        while (n-- > 0) {
            String[] p = sc.nextLine().split(",");
            try {
                cars.add(new UsedCar(p[0], p[1], Integer.parseInt(p[2]), 
                                     Integer.parseInt(p[3]), Double.parseDouble(p[4])));
            } catch (UsedCarException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("List of successfully constructed UsedCar objects:");
        if (cars.isEmpty()) System.out.println("No used cars");
        else cars.forEach(System.out::println);
    }
}
