// STRING002 - Kiểm tra xem chuỗi có chứa chuỗi khác hay không {
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int testCases = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < testCases; i++) {
            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            System.out.println(s1.contains(s2));
        }
        
        scanner.close();
    }
}
