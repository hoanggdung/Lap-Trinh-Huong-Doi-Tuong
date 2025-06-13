// BASIC011 - Tìm số nhỏ nhất trong khoảng cách các dãy số sau 
import java.util.Scanner;

public class Main {
    public static int minGap(int[] a) {
        if (a.length < 2) return 0;
        int min = a[1] - a[0];
        for (int i = 1; i < a.length - 1; i++) {
            int gap = a[i + 1] - a[i];
            if (gap < min) min = gap;
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println(0);
                continue;
            }
            String[] tok = line.split("\\s+");
            int[] arr = new int[tok.length];
            for (int j = 0; j < tok.length; j++) arr[j] = Integer.parseInt(tok[j]);
            System.out.println(minGap(arr));
        }
        sc.close();
    }
}
