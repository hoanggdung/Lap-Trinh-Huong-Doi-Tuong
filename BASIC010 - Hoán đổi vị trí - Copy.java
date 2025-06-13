import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        
        for (int i = 0; i < t; i++) {
            String input = s.nextLine();
            System.out.println(swapAdjacentDigits(input));
        }

        s.close();
    }

    static String swapAdjacentDigits(String n) {
        char[] d = n.toCharArray();
        
        // Nếu độ dài lẻ thì bắt đầu từ 1, nếu chẵn thì từ 0
        int start = (d.length % 2 == 0) ? 0 : 1;
        
        for (int i = start; i < d.length - 1; i += 2) {
            char temp = d[i];
            d[i] = d[i + 1];
            d[i + 1] = temp;
        }

        return new String(d);
    }
}
