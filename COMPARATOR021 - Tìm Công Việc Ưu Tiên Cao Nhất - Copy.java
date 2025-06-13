import java.util.*;

public class Main {
    static class Job {
        String name;
        int priority;
        int time;

        Job(String name, int priority, int time) {
            this.name = name;
            this.priority = priority;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            String name = parts[0];
            int priority = Integer.parseInt(parts[1]);
            int time = Integer.parseInt(parts[2]);
            jobs.add(new Job(name, priority, time));
        }

        Job best = Collections.min(jobs,
            Comparator.comparingInt((Job j) -> j.priority)
                      .thenComparingInt(j -> j.time)
                      .thenComparing(j -> j.name)
        );

        System.out.println(best.name + " " + best.priority + " " + best.time);
    }
}
