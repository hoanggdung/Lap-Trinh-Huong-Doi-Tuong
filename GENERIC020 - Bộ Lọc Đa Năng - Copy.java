 //GENERIC020 - Bộ Lọc Đa Năng 
import java.util.*;
interface Filter<T> {
    boolean test(T t);
}
class GenericFilter {
    public static <T> List<T> filter(List<T> input, Filter<T> filter) {
        List<T> res = new ArrayList<>();
        for (T t : input)
            if (filter.test(t)) res.add(t);
        return res;
    }
}
class IntegerEvenFilter implements Filter<Integer> {
    public boolean test(Integer i) { return i % 2 == 0; }
}
class StringLengthFilter implements Filter<String> {
    int minLen;
    StringLengthFilter(int minLen) { this.minLen = minLen; }
    public boolean test(String s) { return s.length() >= minLen; }
}
class EmployeeSalaryFilter implements Filter<Employee> {
    double minSalary;
    EmployeeSalaryFilter(double minSalary) { this.minSalary = minSalary; }
    public boolean test(Employee e) { return e.getSalary() >= minSalary; }
}
class Employee {
    private String name; private double salary;
    Employee(String name, double salary) { this.name = name; this.salary = salary; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String toString() { return name + " " + salary; }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while(t-- > 0) {
            while(true) {
                String line = sc.nextLine().trim();
                if(line.equals("End")) break;
                String[] parts = line.split(" ");
                String cmd = parts[0];
                if(cmd.equals("FilterIntEven")) {
                    int n = Integer.parseInt(parts[1]);
                    List<Integer> list = new ArrayList<>();
                    for(int i=0; i<n; i++) list.add(Integer.parseInt(parts[2+i]));
                    List<Integer> res = GenericFilter.filter(list, new IntegerEvenFilter());
                    if(res.isEmpty()) System.out.println("None");
                    else for(int x: res) System.out.println(x);
                } else if(cmd.equals("FilterStringLength")) {
                    int minLen = Integer.parseInt(parts[1]);
                    int n = Integer.parseInt(parts[2]);
                    List<String> list = new ArrayList<>();
                    for(int i=0; i<n; i++) list.add(parts[3+i]);
                    List<String> res = GenericFilter.filter(list, new StringLengthFilter(minLen));
                    if(res.isEmpty()) System.out.println("None");
                    else for(String s: res) System.out.println(s);
                } else if(cmd.equals("FilterEmployeeSalary")) {
                    double minSalary = Double.parseDouble(parts[1]);
                    int n = Integer.parseInt(parts[2]);
                    List<Employee> list = new ArrayList<>();
                    int idx = 3;
                    for(int i=0; i<n; i++) {
                        String name = parts[idx++];
                        double salary = Double.parseDouble(parts[idx++]);
                        list.add(new Employee(name, salary));
                    }
                    List<Employee> res = GenericFilter.filter(list, new EmployeeSalaryFilter(minSalary));
                    if(res.isEmpty()) System.out.println("None");
                    else for(Employee e: res) System.out.println(e);
                }
            }
        }
        sc.close();
    }
}


