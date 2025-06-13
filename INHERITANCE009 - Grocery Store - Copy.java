import java.util.*;

// Lớp mô tả một mặt hàng
class Item {
    private String name;
    private double price, discount;

    public Item(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f (-$%.2f)", name, price, discount);
    }
}

// Lớp mô tả nhân viên tính hóa đơn
class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Lớp hóa đơn thông thường
class GroceryBill {
    protected Employee clerk;
    protected List<Item> receipt = new ArrayList<>();
    protected double total;

    public GroceryBill(Employee clerk) {
        this.clerk = clerk;
    }

    public void add(Item item) {
        receipt.add(item);
        total += item.getPrice();
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("items:\n");
        for (Item item : receipt) {
            sb.append("   ").append(item).append("\n");
        }
        sb.append(String.format("total: $%.2f\n", total));
        sb.append("Clerk: ").append(clerk.getName());
        return sb.toString();
    }
}

// Lớp hóa đơn có áp dụng giảm giá
class DiscountBill extends GroceryBill {
    private double discount;

    public DiscountBill(Employee clerk) {
        super(clerk);
    }

    @Override
    public void add(Item item) {
        super.add(item);
        discount += item.getDiscount();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("items:\n");
        for (Item item : receipt) {
            sb.append("   ").append(item).append("\n");
        }
        sb.append(String.format("sub-total: $%.2f\n", total));
        sb.append(String.format("discount: $%.2f\n", discount));
        sb.append(String.format("total: $%.2f\n", total - discount));
        sb.append("Clerk: ").append(clerk.getName());
        return sb.toString();
    }
}

// Lớp chạy chương trình chính
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Grocery Bill");
        GroceryBill b1 = new GroceryBill(e1);
        b1.add(new Item("item 1", 2.3, 0));
        b1.add(new Item("item 2", 3.45, 0));
        System.out.println(b1 + "\n");

        Employee e2 = new Employee("Discount Bill");
        DiscountBill b2 = new DiscountBill(e2);
        b2.add(new Item("item 3", 20, 15));
        b2.add(new Item("item 4", 40, 35));
        b2.add(new Item("item 5", 50, 35));
        System.out.println(b2);
    }
}
