// COMPARATOR022 - Sắp xếp Danh Sách Sinh Viên Đa Tiêu Chí 
import java.util.*;

class Student {
    String name;
    int birthYear;
    double gpa;
    int credit;

    public Student(String name, int birthYear, double gpa, int credit) {
        this.name = name;
        this.birthYear = birthYear;
        this.gpa = gpa;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return name + " " + birthYear + " " + gpa + " " + credit;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split(" ");
            String name = parts[0];
            int birthYear = Integer.parseInt(parts[1]);
            double gpa = Double.parseDouble(parts[2]);
            int credit = Integer.parseInt(parts[3]);
            students.add(new Student(name, birthYear, gpa, credit));
        }

        String criteria = sc.nextLine();

        if (criteria.equals("BY_GPA_CREDIT_NAME")) {
            students.sort((a, b) -> {
                if (Double.compare(b.gpa, a.gpa) != 0) {
                    return Double.compare(b.gpa, a.gpa);  // GPA giảm dần
                }
                if (b.credit != a.credit) {
                    return b.credit - a.credit;          // Credit giảm dần
                }
                return a.name.compareTo(b.name);         // Tên tăng dần
            });
        } else if (criteria.equals("BY_BIRTH_GPA_NAME")) {
            students.sort((a, b) -> {
                if (a.birthYear != b.birthYear) {
                    return a.birthYear - b.birthYear;    // Năm sinh tăng dần
                }
                if (Double.compare(b.gpa, a.gpa) != 0) {
                    return Double.compare(b.gpa, a.gpa);  // GPA giảm dần
                }
                return a.name.compareTo(b.name);         // Tên tăng dần
            });
        }

        for (Student st : students) {
            System.out.println(st);
        }
    }
}

