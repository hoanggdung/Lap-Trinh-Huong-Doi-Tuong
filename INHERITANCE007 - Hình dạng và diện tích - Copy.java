import java.util.*;

// Abstract base class Shape
abstract class Shape {
    public abstract double calculateArea();
    public abstract void displayInfo();
}

// Circle class implementing Shape
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Shape:Circle|Area:%.2f\n", calculateArea());
    }
}

// Rectangle class implementing Shape
class Rectangle extends Shape {
    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    @Override
    public double calculateArea() {
        return width * length;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Shape:Rectangle|Area:%.2f\n", calculateArea());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); 
        sc.nextLine(); // consume leftover newline

        for (int i = 0; i < t; i++) {
            String type = sc.next();
            if (type.equals("Rectangle")) {
                double width = sc.nextDouble();
                double length = sc.nextDouble();
                Shape shape = new Rectangle(width, length);
                shape.displayInfo();
            } else if (type.equals("Circle")) {
                double radius = sc.nextDouble();
                Shape shape = new Circle(radius);
                shape.displayInfo();
            }
            sc.nextLine(); // consume leftover newline
        }

        sc.close();
    }
}
