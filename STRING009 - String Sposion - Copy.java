import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());

        while (t-- > 0) {
            String input = s.nextLine();
            System.out.println(stringSplosion(input));
        }

        s.close();
    }

    static String stringSplosion(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= str.length(); i++) {
            result.append(str, 0, i);
        }
        return result.toString();
    }
}
