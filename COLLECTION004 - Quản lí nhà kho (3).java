import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Item {
    private String product;
    private int quantity;
    private int unitPrice;

    public Item(String product, int quantity, int unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public int getUnitPrice() { return unitPrice; }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
    }

    public int getPrice() {
        return this.quantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return "Product: " + this.product + " has quantity " + this.quantity + " with price: " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(product, item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}

class Warehouse {
    private List<Item> items;

    public Warehouse() {
        this.items = new ArrayList<>();
    }

    public void importProduct(Item item) {
        this.items.add(item);
    }

    public void removeProduct(Item itemToRemove) {
        Item itemInWarehouse = findItem(itemToRemove.getProduct());
        if (itemInWarehouse != null) {
            int currentQuantity = itemInWarehouse.getQuantity();
            int quantityToRemove = itemToRemove.getQuantity();
            itemInWarehouse.setQuantity(currentQuantity - quantityToRemove);
        }
    }

    public void decreaseQuantityInWarehouseByOne() {
        for (Item item : this.items) {
            item.setQuantity(item.getQuantity() - 1);
        }
    }

    public void takeFromItemToWarehouse(List<Item> itemsToCheck, String product, int quantity) {
        Item itemFromCheckList = null;
        for (Item checkItem : itemsToCheck) {
            if (checkItem.getProduct().equals(product)) {
                itemFromCheckList = checkItem;
                break;
            }
        }
        if (itemFromCheckList == null) return;
        Item itemInWarehouse = findItem(product);
        if (itemInWarehouse == null) return;
        int availableQuantityInCheckList = itemFromCheckList.getQuantity();
        int actualQuantityToAdd = Math.min(quantity, availableQuantityInCheckList);
        itemInWarehouse.setQuantity(itemInWarehouse.getQuantity() + actualQuantityToAdd);
    }

    private Item findItem(String productName) {
        for (Item item : this.items) {
            if (item.getProduct().equals(productName)) return item;
        }
        return null;
    }

    public void printWarehouseState() {
        System.out.println("--- Warehouse State ---");
        if (items.isEmpty()) System.out.println("Warehouse is empty.");
        else for (Item item : items) System.out.println(item);
        System.out.println("-----------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        Item milk = new Item("milk", 4, 2);
        Item buttermilk = new Item("buttermilk", 10, 2);
        System.out.println(milk);
        System.out.println(buttermilk);

        Warehouse warehouse = new Warehouse();
        warehouse.importProduct(milk);
        warehouse.importProduct(buttermilk);

        warehouse.removeProduct(new Item("milk", 1, 0));
        warehouse.removeProduct(new Item("buttermilk", 3, 0));
        System.out.println(milk);
        System.out.println(buttermilk);

        warehouse.decreaseQuantityInWarehouseByOne();
        System.out.println(milk);
        System.out.println(buttermilk);

        List<Item> itemsToCheck = new ArrayList<>();
        itemsToCheck.add(new Item("milk", 2, 0));
        itemsToCheck.add(new Item("buttermilk", 6, 0));
        warehouse.takeFromItemToWarehouse(itemsToCheck, "milk", 5);
        warehouse.takeFromItemToWarehouse(itemsToCheck, "buttermilk", 1);
        System.out.println(milk);
        System.out.println(buttermilk);
    }
}
