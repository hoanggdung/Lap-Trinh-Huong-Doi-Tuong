import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Point> points = new LinkedHashMap<>();
        String startPointName = null;
        int i = 0;
        while (true) {
            i++;
            try {
                String pointName = scanner.nextLine().trim();
                startPointName = pointName;
                String[] point = scanner.nextLine().trim().split("\\s+");
                if (point.length < 3) break;
                if (points.containsKey(pointName) || pointName.isEmpty()) pointName += i;
                double x = Double.parseDouble(point[0]);
                double y = Double.parseDouble(point[1]);
                double z = Double.parseDouble(point[2]);
                points.put(pointName, new Point(x, y, z));
            } catch (Exception e) {
                break;
            }
        }

        if (points.size() <= 1 || startPointName == null || !points.containsKey(startPointName)) {
            System.out.print("invalid input");
            return;
        }

        Point startPoint = points.get(startPointName);
        for (Map.Entry<String, Point> entry : points.entrySet()) {
            if (entry.getKey().equals(startPointName)) continue;
            Point endPoint = entry.getValue();
            Line line = new Line(startPoint, endPoint);
            double length = line.getLength();
            if (length == 0) {
                System.out.println("Line " + startPointName + entry.getKey() + ": same point");
            } else {
                System.out.printf("Line %s%s: %.2f\n", startPointName, entry.getKey().replaceAll("\\d+", ""), length);
            }
        }
    }
}

class Point {
    private double x, y, z;
    public Point(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
}

class Line extends Point {
    private Point start, end;
    public Line(Point start, Point end) {
        super(0, 0, 0);
        this.start = start;
        this.end = end;
    }
    public double getLength() {
        double x1 = Math.pow(start.getX() - end.getX(),2);
        double x2 = Math.pow(start.getY() - end.getY(), 2);
        double x3 = Math.pow(start.getZ() - end.getZ(), 2);
        return Math.sqrt(x1 + x2 + x3);
    }
}
