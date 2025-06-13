  // Giai thừa của các số trong một khoảng
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println("Factorials of numbers from 1 to " + n + ":");

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            long factorial = 1;
            for (int j = 1; j <= i; j++) {
                factorial *= j;
            }
            System.out.println(i + "! = " + factorial);
            sum += factorial;
        }

        System.out.println("The sum of these factorials is: " + sum);
        scanner.close();
    }
}