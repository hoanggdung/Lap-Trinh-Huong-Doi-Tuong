// INHERITANCE003 - Con người
class Person {
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }
}
class Student extends Person {
    private int credits = 0;

    public Student(String name, String address, String id) {
        super(name, address);
    }

    public void study() {
        credits++;
    }

    public int credits() {
        return credits;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028", "S12345");
        System.out.println(s);
        System.out.println("Study credits " + s.credits());
        s.study();
        System.out.println("Study credits " + s.credits());
    }
}
