import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());

        while (t-- > 0) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            Map<Integer, Integer> valueFrequencies = new HashMap<>();

            for (int i = 1; i < parts.length; i += 2) {
                try {
                    int value = Integer.parseInt(parts[i]);
                    valueFrequencies.put(value, valueFrequencies.getOrDefault(value, 0) + 1);
                } catch (NumberFormatException e) {
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }

            int minFrequency = Integer.MAX_VALUE;
            int rarestValue = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : valueFrequencies.entrySet()) {
                int currentValue = entry.getKey();
                int currentFrequency = entry.getValue();

                if (currentFrequency < minFrequency) {
                    minFrequency = currentFrequency;
                    rarestValue = currentValue;
                } else if (currentFrequency == minFrequency) {
                    if (currentValue < rarestValue) {
                        rarestValue = currentValue;
                    }
                }
            }

            if (minFrequency != Integer.MAX_VALUE) {
                System.out.println(rarestValue);
            }
        }

        scanner.close();
    }
}
