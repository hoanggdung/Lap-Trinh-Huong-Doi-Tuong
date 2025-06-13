import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Đọc số lượng bộ dữ liệu kiểm tra
        int T = sc.nextInt();
        
        // Duyệt qua từng bộ dữ liệu
        for (int i = 0; i < T; i++) {
            // Đọc tọa độ x và y
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            
            // Xác định góc phần tư
            if (x > 0 && y > 0) {
                System.out.println(1);
            } else if (x < 0 && y > 0) {
                System.out.println(2);
            } else if (x < 0 && y < 0) {
                System.out.println(3);
            } else if (x > 0 && y < 0) {
                System.out.println(4);
            } else {
                System.out.println(0);  // Điểm nằm trên trục tọa độ
            }
        }
        
        sc.close();
    }
}