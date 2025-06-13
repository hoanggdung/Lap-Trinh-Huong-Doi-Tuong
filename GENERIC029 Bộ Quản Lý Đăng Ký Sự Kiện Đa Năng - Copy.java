// GENERIC029 Bộ Quản Lý Đăng Ký Sự Kiện Đa Năng {
import java.util.*;
import java.util.function.Predicate;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class EventRegistry<E, P> {
    private Map<E, LinkedHashSet<P>> eventToPeople = new LinkedHashMap<>();
    private Map<P, LinkedHashSet<E>> personToEvents = new LinkedHashMap<>();

    public void register(E event, P person) {
        eventToPeople.putIfAbsent(event, new LinkedHashSet<>());
        eventToPeople.get(event).add(person);

        personToEvents.putIfAbsent(person, new LinkedHashSet<>());
        personToEvents.get(person).add(event);
    }

    public void unregister(E event, P person) {
        if (eventToPeople.containsKey(event)) {
            eventToPeople.get(event).remove(person);
            if (eventToPeople.get(event).isEmpty()) {
                eventToPeople.remove(event);
            }
        }

        if (personToEvents.containsKey(person)) {
            personToEvents.get(person).remove(event);
            if (personToEvents.get(person).isEmpty()) {
                personToEvents.remove(person);
            }
        }
    }

    public boolean isRegistered(E event, P person) {
        return eventToPeople.containsKey(event) && eventToPeople.get(event).contains(person);
    }

    public Set<P> getPeople(E event) {
        if (!eventToPeople.containsKey(event)) return Collections.emptySet();
        return eventToPeople.get(event);
    }

    public Set<E> getEvents(P person) {
        if (!personToEvents.containsKey(person)) return Collections.emptySet();
        return personToEvents.get(person);
    }

    public Set<P> filterPeople(E event, Predicate<P> predicate) {
        Set<P> people = getPeople(event);
        LinkedHashSet<P> filtered = new LinkedHashSet<>();
        for (P p : people) {
            if (predicate.test(p)) {
                filtered.add(p);
            }
        }
        return filtered;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            EventRegistry<String, Person> registry = new EventRegistry<>();

            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;

                String[] parts = line.split(" ");
                String cmd = parts[0];

                switch (cmd) {
                    case "Register": {
                        // Register event name age
                        String event = parts[1];
                        String name = parts[2];
                        int age = Integer.parseInt(parts[3]);
                        registry.register(event, new Person(name, age));
                        break;
                    }
                    case "Unregister": {
                        String event = parts[1];
                        String name = parts[2];
                        int age = Integer.parseInt(parts[3]);
                        registry.unregister(event, new Person(name, age));
                        break;
                    }
                    case "IsRegistered": {
                        String event = parts[1];
                        String name = parts[2];
                        int age = Integer.parseInt(parts[3]);
                        boolean res = registry.isRegistered(event, new Person(name, age));
                        System.out.println(res);
                        break;
                    }
                    case "ListPeople": {
                        String event = parts[1];
                        for (Person p : registry.getPeople(event)) {
                            System.out.println(p);
                        }
                        break;
                    }
                    case "ListEvents": {
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        for (String e : registry.getEvents(new Person(name, age))) {
                            System.out.println(e);
                        }
                        break;
                    }
                    case "FilterPeopleByAge": {
                        String event = parts[1];
                        int minAge = Integer.parseInt(parts[2]);
                        Set<Person> filtered = registry.filterPeople(event, p -> p.getAge() >= minAge);
                        for (Person p : filtered) {
                            System.out.println(p);
                        }
                        break;
                    }
                    default:
                        // ignore unknown commands
                        break;
                }
            }
        }

        sc.close();
    }
}
  

