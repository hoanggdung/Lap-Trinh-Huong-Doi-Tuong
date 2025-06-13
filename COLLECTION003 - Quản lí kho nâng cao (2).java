import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
class Warehouse {
    private HashMap<String, Integer> stocks;
    public Warehouse() {
        stocks = new HashMap<>();
    }
    public void addProduct(String product, int price, int stock) {
        // Không lưu giá, chỉ lưu tồn kho
        stocks.put(product, stock);
    }

    public int price(String product) {
        // Vì không lưu giá nên luôn trả -99
        return -99;
    }

    public int stock(String product) {
        return stocks.getOrDefault(product, 0);
    }

    public boolean take(String product) {
        if (!stocks.containsKey(product)) return false;

        int currentStock = stocks.get(product);
        if (currentStock > 0) {
            stocks.put(product, currentStock - 1);
            return true;
        } else {
            stocks.put(product, 0); // Không cho âm
            return false;
        }
    }

    public Set<String> products() {
        return stocks.keySet();
    }
}
public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // Thêm sản phẩm
        warehouse.addProduct("milk", 3, 10);
        warehouse.addProduct("coffee", 5, 6);
        warehouse.addProduct("buttermilk", 2, 2);
        warehouse.addProduct("yogurt", 2, 20);

        // Lấy sản phẩm
        warehouse.take("buttermilk"); // stock: 1
        warehouse.take("milk");       // stock: 9
        warehouse.take("buttermilk"); // stock: 0

        // In sản phẩm còn tồn kho
        for (String product : warehouse.products()) {
            if (warehouse.stock(product) > 0) {
                System.out.println(product);
            }
        }
    }
}