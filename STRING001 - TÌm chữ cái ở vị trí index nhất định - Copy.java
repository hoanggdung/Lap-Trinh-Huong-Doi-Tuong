import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Đọc số lượng test case
        int t = scanner.nextInt();
        scanner.nextLine();  // Đọc ký tự newline còn lại sau nextInt
        
        // Duyệt qua từng test case
        for (int i = 0; i < t; i++) {
            // Đọc chuỗi và chỉ số index
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String s = parts[0];  // Chuỗi
            int index = Integer.parseInt(parts[1]);  // Chỉ số index
            
            // Lấy ký tự tại index và in ra kết quả
            char character = s.charAt(index);
            System.out.println("The character at position " + index + " is " + character);
        }
        
        scanner.close();  // Đóng scanner
    }
}