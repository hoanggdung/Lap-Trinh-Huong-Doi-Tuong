import java.util.Scanner;
public class Main {
    public static <T> int findFirstMatch(T[] array, T target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();

        while (testCase-- > 0) {
            String input = sc.nextLine();
            String[] parts = input.split(" \\| ");
            String[] array = parts[0].split(" ");
            String target = parts[1];

            int result = findFirstMatch(array, target);
            System.out.println(result);
        }
        sc.close();
    }
}