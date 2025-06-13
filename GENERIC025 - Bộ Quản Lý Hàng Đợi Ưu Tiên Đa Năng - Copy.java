// GENERIC025 - Bộ Quản Lý Hàng Đợi Ưu Tiên Đa Năng 
import java.util.*;

interface PriorityFunction<T> {
    int getPriority(T t);
}

class GenericPriorityQueue<T> {
    private PriorityQueue<T> queue;

    public GenericPriorityQueue(Comparator<T> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    public void add(T item) {
        queue.add(item);
    }

    public T poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class Patient {
    private String name;
    private int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return name + " " + severity;
    }
}

class Task {
    private String description;
    private int deadline;

    public Task(String description, int deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return description + " " + deadline;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        GenericPriorityQueue<Patient> patientQueue = null;
        GenericPriorityQueue<Task> taskQueue = null;

        while (t-- > 0) {
            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;

                String[] parts = line.split(" ");

                switch (parts[0]) {
                    case "NewPatientQueue":
                        // severity giảm dần: comparator sắp xếp descending severity
                        patientQueue = new GenericPriorityQueue<>(
                            (a, b) -> Integer.compare(b.getSeverity(), a.getSeverity())
                        );
                        break;

                    case "AddPatient":
                        if (patientQueue != null) {
                            String name = parts[1];
                            int severity = Integer.parseInt(parts[2]);
                            patientQueue.add(new Patient(name, severity));
                        }
                        break;

                    case "PollPatient":
                        if (patientQueue == null || patientQueue.isEmpty()) {
                            System.out.println("Empty");
                        } else {
                            System.out.println(patientQueue.poll());
                        }
                        break;

                    case "NewTaskQueue":
                        // deadline tăng dần: comparator sắp xếp ascending deadline
                        taskQueue = new GenericPriorityQueue<>(
                            Comparator.comparingInt(Task::getDeadline)
                        );
                        break;

                    case "AddTask":
                        if (taskQueue != null) {
                            String description = parts[1];
                            int deadline = Integer.parseInt(parts[2]);
                            taskQueue.add(new Task(description, deadline));
                        }
                        break;

                    case "PollTask":
                        if (taskQueue == null || taskQueue.isEmpty()) {
                            System.out.println("Empty");
                        } else {
                            System.out.println(taskQueue.poll());
                        }
                        break;

                    default:
                        // Không làm gì với lệnh không xác định
                        break;
                }
            }
        }
        sc.close();
    }
}


