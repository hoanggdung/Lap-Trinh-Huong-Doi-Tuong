import java.util.*; // Pangram

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        while (n-- > 0) {
            String s = sc.nextLine();
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) set.add(c);
            System.out.println(set.size() == 26);
        }
    }
}