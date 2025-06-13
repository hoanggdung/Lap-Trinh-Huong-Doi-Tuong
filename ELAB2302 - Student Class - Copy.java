// ELAB2302 - Student Class {
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private static int studentCount = 0;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        studentCount++;
    }

    public void display() {
        if (this.age >= 18) {
            System.out.println(this.name);
        }
    }

    public static int getStudentCount() {
        return studentCount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            if (!scanner.hasNextLine()) {
                break;
            }
            String ageString = scanner.nextLine();

            try {
                int age = Integer.parseInt(ageString);
                Student student = new Student(name, age);
                student.display();
            } catch (NumberFormatException e) {
                continue;
            }
        }

        System.out.println(Student.getStudentCount());
        scanner.close();
    }
}
