// BASIC005 - Tính tổng 5 số {
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] input = scanner.nextLine().split(" ");
            int sum = 0;
            for (String num : input) {
                sum += Integer.parseInt(num);
            }
            System.out.println(sum);
        }
        scanner.close();
    }
}
