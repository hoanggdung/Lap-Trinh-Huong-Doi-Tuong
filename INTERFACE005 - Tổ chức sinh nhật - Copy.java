import java.util.*;

interface Birthable {
    String getBirthDate();
}

class Citizen implements Birthable {
    private String birthDate;
    public Citizen(String name, int age, String id, String birthDate) {
        this.birthDate = birthDate;
    }
    public String getBirthDate() {
        return birthDate;
    }
}

class Pet implements Birthable {
    private String birthDate;
    public Pet(String name, String birthDate) {
        this.birthDate = birthDate;
    }
    public String getBirthDate() {
        return birthDate;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Birthable> list = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.equals("End")) break;
            String[] p = line.split(" ");
            if (p[0].equals("Citizen"))
                list.add(new Citizen(p[1], Integer.parseInt(p[2]), p[3], p[4]));
            else if (p[0].equals("Pet"))
                list.add(new Pet(p[1], p[2]));
        }
        String year = sc.nextLine();
        for (Birthable b : list)
            if (b.getBirthDate().endsWith(year))
                System.out.println(b.getBirthDate());
    }
}
