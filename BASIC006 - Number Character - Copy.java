 //BASIC006 - Number Character 
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            String line = sc.nextLine();
            int count = 0;
            for (char c : line.toCharArray())
                if (Character.isDigit(c)) count++;
            System.out.println(count);
        }
        sc.close();
    }
}


