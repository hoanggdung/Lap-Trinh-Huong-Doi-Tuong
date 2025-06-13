// INHERITANCE008 - Tín chỉ

// Lớp cha Person
class Person {
    protected String name, address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }
}

// Lớp Student kế thừa Person
class Student extends Person {
    private int credits = 0;

    public Student(String name, String address) {
        super(name, address);
    }

    public void study() {
        credits++;
    }

    public int getCredits() {
        return credits;
    }
}

// Lớp chính Main
public class Main {
    public static void main(String[] args) {
        Student s = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");

        System.out.println(s);
        System.out.println("Study credits " + s.getCredits());

        s.study();

        System.out.println("Study credits " + s.getCredits());
    }
}
