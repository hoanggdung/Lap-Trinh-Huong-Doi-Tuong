class MyStuff { // ELAB2303 - Class MyStuff
    private String name;
    MyStuff(String name) { this.name = name; }
    public boolean equals(Object o) {
        if (!(o instanceof MyStuff)) return false;
        return name.equals(((MyStuff) o).name);
    }
}

public class Main {
    public static void main(String[] args) {
        MyStuff m1 = new MyStuff("PC"), m2 = new MyStuff("PC");
        System.out.println("value compared: " + (m1.equals(m2) ? "same" : "different"));
        System.out.println("reference compared: " + (m1 == m2 ? "same" : "different"));
    }
}
