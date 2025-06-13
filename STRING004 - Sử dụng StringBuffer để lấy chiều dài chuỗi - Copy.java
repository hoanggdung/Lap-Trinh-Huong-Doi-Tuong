// STRING004 - Sử dụng StringBuffer để lấy chiều dài chuỗi 
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int testCases = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < testCases; i++) {
            StringBuffer s = new StringBuffer(scanner.nextLine());
            System.out.println(s.length());
        } 
        scanner.close();
    }
}
