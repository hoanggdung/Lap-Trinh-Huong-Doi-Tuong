import java.util.*;
interface Identifiable {
    String getId();
}
class Human implements Identifiable {
    private String name, id;
    private int age;
    public Human(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
class Robot implements Identifiable {
    private String model, id;
    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Identifiable> identifiables = new ArrayList<>();
        scanner.nextLine(); // Bỏ qua dòng test case
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("End")) break;
            String[] parts = line.split(" ");
            if (parts[0].equals("Human")) {
                identifiables.add(new Human(parts[1], Integer.parseInt(parts[2]), parts[3]));
            } else if (parts[0].equals("Robot")) {
                String model = parts[1];
                if (parts.length == 4) model += " " + parts[2];
                String id = parts[parts.length - 1];
                identifiables.add(new Robot(model, id));
            }
        }
        String suffix = scanner.nextLine();
        for (Identifiable entity : identifiables)
            if (entity.getId().endsWith(suffix))
                System.out.println(entity.getId());
    }
}
