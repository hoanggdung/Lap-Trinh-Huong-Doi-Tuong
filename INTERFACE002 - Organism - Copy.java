import java.util.ArrayList;
import java.util.Scanner;

// Interface Moveable
interface Moveable {
    void move(int dx, int dy);
}

// Class Organism
class Organism implements Moveable {
    private int x, y;

    // Constructor
    public Organism(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Phương thức move thay đổi tọa độ của Organism
    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Phương thức toString() trả về tọa độ theo format x:___; y:___
    @Override
    public String toString() {
        return "x:" + x + ";y:" + y;
    }
}

// Class Herd quản lý các Organism
class Herd {
    private ArrayList<Organism> organisms;

    // Constructor
    public Herd() {
        organisms = new ArrayList<>();
    }

    // Phương thức addToHerd thêm Organism vào Herd
    public void addToHerd(Moveable moveable) {
        if (moveable instanceof Organism) {
            organisms.add((Organism) moveable);
        }
    }

    // Phương thức move thay đổi tọa độ của tất cả các Organism trong Herd
    public void move(int dx, int dy) {
        for (Organism organism : organisms) {
            organism.move(dx, dy);
        }
    }

    // Phương thức toString() trả về danh sách Organism trong Herd
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organism organism : organisms) {
            sb.append(organism.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}

// Class Main để thực hiện các bước
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đọc số lượng test case
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự newline còn lại

        Herd herd = new Herd();

        // Đọc tọa độ của mỗi Organism và thêm vào Herd
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Organism organism = new Organism(x, y);
            herd.addToHerd(organism);
        }

        // Di chuyển tất cả các Organism trong Herd
        herd.move(1, 1);

        // In danh sách Organism trong Herd sau khi di chuyển
        System.out.print(herd.toString());

        scanner.close();
    }
}