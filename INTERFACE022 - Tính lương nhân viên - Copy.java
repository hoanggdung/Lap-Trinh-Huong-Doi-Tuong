import java.util.*;
import java.util.regex.*;

interface Identifiable {
    String getId();
}

interface Payable {
    double calculateMonthlyPay();
}

class FullTime implements Identifiable, Payable {
    private String name, id;
    private double salary;

    public FullTime(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double calculateMonthlyPay() {
        return salary;
    }
}

class PartTime implements Identifiable, Payable {
    private String name, id;
    private double rate;
    private int hours;

    public PartTime(String name, String id, double rate, int hours) {
        this.name = name;
        this.id = id;
        this.rate = rate;
        this.hours = hours;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double calculateMonthlyPay() {
        return rate * hours;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payable> list = new ArrayList<>();

        sc.nextLine(); // Skip header line if any

        Pattern fullTimePattern = Pattern.compile("FullTime\\s+\"(.*?)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)");
        Pattern partTimePattern = Pattern.compile("PartTime\\s+\"(.*?)\"\\s+(\\S+)\\s+(\\d+(?:\\.\\d+)?)\\s+(\\d+)");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("Calculate")) break;

            Matcher ftMatcher = fullTimePattern.matcher(line);
            Matcher ptMatcher = partTimePattern.matcher(line);

            if (ftMatcher.matches()) {
                String name = ftMatcher.group(1);
                String id = ftMatcher.group(2);
                double salary = Double.parseDouble(ftMatcher.group(3));
                list.add(new FullTime(name, id, salary));
            } else if (ptMatcher.matches()) {
                String name = ptMatcher.group(1);
                String id = ptMatcher.group(2);
                double rate = Double.parseDouble(ptMatcher.group(3));
                int hours = Integer.parseInt(ptMatcher.group(4));
                list.add(new PartTime(name, id, rate, hours));
            }
        }

        double total = 0;
        for (Payable employee : list) {
            total += employee.calculateMonthlyPay();
        }

        System.out.printf("Total Monthly Payroll: %.2f\n", total);
    }
}
