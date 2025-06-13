import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Đọc số lượng test case
        int t = Integer.parseInt(scanner.nextLine().trim());
        
        // Lặp qua từng test case
        for (int i = 0; i < t; i++) {
            // Đọc chuỗi s1 và s2
            String s1 = scanner.nextLine().trim();
            String s2 = scanner.nextLine().trim();
            
            // So sánh chuỗi sau khi chuyển về chữ thường
            if (s1.equalsIgnoreCase(s2)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

        scanner.close();
    }
}