import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    double distanceTo(Point p) {
        return Math.hypot(x - p.x, y - p.y);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            Point a = new Point(sc.nextInt(), sc.nextInt());
            Point b = new Point(sc.nextInt(), sc.nextInt());
            a.move(1, 1);
            System.out.printf("%.2f\n", a.distanceTo(b));
        }

        sc.close();
    }
}
