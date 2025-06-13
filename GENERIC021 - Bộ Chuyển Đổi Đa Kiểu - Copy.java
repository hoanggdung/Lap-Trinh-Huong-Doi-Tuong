// GENERIC021 - Bộ Chuyển Đổi Đa Kiểu 
import java.util.*;
interface Mapper<T,R> { R map(T t); }
class GenericMapper {
    public static <T,R> List<R> map(List<T> in, Mapper<T,R> m) {
        List<R> res = new ArrayList<>();
        for (T t : in) res.add(m.map(t));
        return res;
    }
}
class IntegerToStringMapper implements Mapper<Integer,String> {
    public String map(Integer i) { return i.toString(); }
}
class StringToLengthMapper implements Mapper<String,Integer> {
    public Integer map(String s) { return s.length(); }
}
class EmployeeToStringMapper implements Mapper<Employee,String> {
    public String map(Employee e) { return e.getName()+":"+e.getSalary(); }
}
class Employee {
    private String name; private double salary;
    Employee(String n, double s) { name=n; salary=s; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String toString() { return name+":"+salary; }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=Integer.parseInt(sc.nextLine());
        while(t-->0)
            while(true) {
                String line=sc.nextLine().trim();
                if(line.equals("End")) break;
                String[] p=line.split(" ");
                String cmd=p[0];
                if(cmd.equals("MapIntToString")) {
                    int n=Integer.parseInt(p[1]);
                    List<Integer> list=new ArrayList<>();
                    for(int i=0;i<n;i++) list.add(Integer.parseInt(p[2+i]));
                    GenericMapper.map(list,new IntegerToStringMapper()).forEach(System.out::println);
                } else if(cmd.equals("MapStringToLength")) {
                    int n=Integer.parseInt(p[1]);
                    List<String> list=new ArrayList<>();
                    for(int i=0;i<n;i++) list.add(p[2+i]);
                    GenericMapper.map(list,new StringToLengthMapper()).forEach(System.out::println);
                } else if(cmd.equals("MapEmployeeToString")) {
                    int n=Integer.parseInt(p[1]), idx=2;
                    List<Employee> list=new ArrayList<>();
                    for(int i=0;i<n;i++) {
                        list.add(new Employee(p[idx++], Double.parseDouble(p[idx++])));
                    }
                    GenericMapper.map(list,new EmployeeToStringMapper()).forEach(System.out::println);
                }
            }
        sc.close();
    }
}


