import java.util.HashMap;

class Warehouse {
    private HashMap<String, Integer> stocks;

    public Warehouse() {
        stocks = new HashMap<>();
    }

    public void addProduct(String product, int price, int stock) {
        // Chỉ lưu số lượng tồn kho, bỏ qua giá như yêu cầu
        stocks.put(product, stock);
    }

    public int price(String product) {
        // Không lưu giá, nên trả -99 theo yêu cầu
        return -99;
    }

    public int stock(String product) {
        return stocks.getOrDefault(product, 0);
    }

    public boolean take(String product) {
        if (stocks.containsKey(product) && stocks.get(product) > 0) {
            stocks.put(product, stocks.get(product) - 1);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        
        // Thêm sản phẩm coffee với price = 5 và stock = 1
        warehouse.addProduct("coffee", 5, 1);

        // In số lượng tồn kho ban đầu
        System.out.println("stock:");
        System.out.println("coffee:  " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));

        // Thử lấy sản phẩm
        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking sugar " + warehouse.take("sugar"));

        // In tồn kho sau khi lấy
        System.out.println("stock:");
        System.out.println("coffee:  " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));
    }
}