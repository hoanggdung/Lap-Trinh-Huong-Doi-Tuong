import java.util.*;

interface Callable { String call(); }
interface Browsable { String browse(); }

class Smartphone implements Callable, Browsable {
    List<String> numbers, urls;

    Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    public String call() {
        StringBuilder sb = new StringBuilder();
        for (String n : numbers)
            sb.append(n.matches("\\d+") ? "Calling... " + n : "Invalid number!").append("\n");
        return sb.toString();
    }

    public String browse() {
        StringBuilder sb = new StringBuilder();
        for (String u : urls)
            sb.append(u.matches(".*\\d.*") ? "Invalid URL!" : "Browsing: " + u + "!").append("\n");
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine(); // bỏ qua test case
        List<String> numbers = Arrays.asList(sc.nextLine().split(" "));
        List<String> urls = Arrays.asList(sc.nextLine().split(" "));
        Smartphone phone = new Smartphone(numbers, urls);
        System.out.print(phone.call());
        System.out.print(phone.browse());
    }
}
