import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Item {
    private String name;
    private int weight;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Item(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

abstract class Box {
    public abstract void add(Item item);
    public abstract boolean isInBox(Item item);
}

class BoxWithMaxWeight extends Box {
    private List<Item> items;
    private final int maxWeight;

    public BoxWithMaxWeight(int maxWeight) {
        if (maxWeight < 0) {
            throw new IllegalArgumentException("Max weight cannot be negative.");
        }
        this.maxWeight = maxWeight;
        this.items = new ArrayList<>();
    }

    private int getCurrentWeight() {
        int currentWeight = 0;
        for (Item item : this.items) {
            currentWeight += item.getWeight();
        }
        return currentWeight;
    }

    @Override
    public void add(Item item) {
        if (item == null) return;
        int currentWeight = getCurrentWeight();
        if (currentWeight + item.getWeight() <= this.maxWeight) {
            this.items.add(item);
        }
    }

    @Override
    public boolean isInBox(Item item) {
        if (item == null || item.getName() == null) {
            return false;
        }
        String nameToCheck = item.getName();
        for (Item existingItem : this.items) {
            if (existingItem != null && existingItem.getName() != null && existingItem.getName().equals(nameToCheck)) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        BoxWithMaxWeight box = new BoxWithMaxWeight(10);

        Item item1 = new Item("Saludo", 5);
        Item item2 = new Item("Pirkka", 5);
        Item item3 = new Item("Kopi Luwak", 5);

        box.add(item1);
        box.add(item2);
        box.add(item3);

        Item checkItem1 = new Item("Saludo");
        Item checkItem2 = new Item("Pirkka");
        Item checkItem3 = new Item("Kopi Luwak");

        System.out.println(box.isInBox(checkItem1));
        System.out.println(box.isInBox(checkItem2));
        System.out.println(box.isInBox(checkItem3));
    }
}
