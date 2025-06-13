class Dog {
    private String name, breed;
    private int age;

    void setName(String name) {
        this.name = name;
    }

    void setBreed(String breed) {
        this.breed = breed;
    }

    void setAge(int age) {
        this.age = age;
    }

    void printDogDetail() {
        System.out.println("Name: " + name + " -- Breed: " + breed + " -- Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.setName("Micky");
        d.setBreed("Husky");
        d.setAge(12);
        d.printDogDetail();
    }
}
