import java.util.*;
import java.util.stream.Collectors;

interface Filter<T> {
    boolean test(T t);
}

interface Mapper<T, R> {
    R map(T t);
}

class ListProcessor {
    public static <T> List<T> filter(List<T> input, Filter<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : input) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T, R> List<R> map(List<T> input, Mapper<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : input) {
            result.add(mapper.map(item));
        }
        return result;
    }

    public static <T> List<T> sort(List<T> input, Comparator<T> comparator) {
        List<T> sorted = new ArrayList<>(input);
        sorted.sort(comparator);
        return sorted;
    }
}

class Student {
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return name + " " + gpa;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine(); // Bỏ số test case, luôn = 1

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equals("End")) break;

            String[] tokens = line.split(" ");
            String command = tokens[0];

            switch (command) {
                case "FilterGpaAbove": {
                    double threshold = Double.parseDouble(tokens[1]);
                    int n = Integer.parseInt(tokens[2]);
                    List<Student> students = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String name = tokens[3 + i * 2];
                        double gpa = Double.parseDouble(tokens[4 + i * 2]);
                        students.add(new Student(name, gpa));
                    }
                    List<Student> result = ListProcessor.filter(students,
                            s -> s.getGpa() > threshold);
                    printList(result);
                    break;
                }

                case "MapStudentToName": {
                    int n = Integer.parseInt(tokens[1]);
                    List<Student> students = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String name = tokens[2 + i * 2];
                        double gpa = Double.parseDouble(tokens[3 + i * 2]);
                        students.add(new Student(name, gpa));
                    }
                    List<String> result = ListProcessor.map(students, Student::getName);
                    printList(result);
                    break;
                }

                case "SortStudentByGpa": {
                    int n = Integer.parseInt(tokens[1]);
                    List<Student> students = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        String name = tokens[2 + i * 2];
                        double gpa = Double.parseDouble(tokens[3 + i * 2]);
                        students.add(new Student(name, gpa));
                    }
                    List<Student> result = ListProcessor.sort(students, Comparator.comparingDouble(Student::getGpa));
                    printList(result);
                    break;
                }

                case "FilterIntOdd": {
                    int n = Integer.parseInt(tokens[1]);
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        list.add(Integer.parseInt(tokens[2 + i]));
                    }
                    List<Integer> result = ListProcessor.filter(list, x -> x % 2 != 0);
                    printList(result);
                    break;
                }

                case "SortIntAsc": {
                    int n = Integer.parseInt(tokens[1]);
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        list.add(Integer.parseInt(tokens[2 + i]));
                    }
                    List<Integer> result = ListProcessor.sort(list, Integer::compareTo);
                    printList(result);
                    break;
                }

                case "MapIntToSquare": {
                    int n = Integer.parseInt(tokens[1]);
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        list.add(Integer.parseInt(tokens[2 + i]));
                    }
                    List<Integer> result = ListProcessor.map(list, x -> x * x);
                    printList(result);
                    break;
                }

                default:
                    System.out.println("Unknown command: " + command);
            }
        }
        sc.close();
    }

    private static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}