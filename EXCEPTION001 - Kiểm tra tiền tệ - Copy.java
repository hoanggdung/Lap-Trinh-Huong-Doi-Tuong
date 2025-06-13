// EXCEPTION001 - Kiểm tra tiền tệ {
import java.util.Scanner;

class Amount {
    private String currency;
    private int amount;
    public Amount(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }
    public int add(Amount other) throws Exception {
        if (!this.currency.equals(other.currency)) {
            throw new Exception("Currency doesn't match");
        }
        return this.amount + other.amount;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < testCases; i++) {
            String[] input = scanner.nextLine().split(" ");
            Amount amount1 = new Amount(input[0], Integer.parseInt(input[1]));
            Amount amount2 = new Amount(input[2], Integer.parseInt(input[3]));
            try {
                System.out.println(amount1.add(amount2));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}

