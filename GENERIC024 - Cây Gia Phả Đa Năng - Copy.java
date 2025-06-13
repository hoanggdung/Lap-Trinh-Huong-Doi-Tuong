// GENERIC024 - Cây Gia Phả Đa Năng 
import java.util.*;

class TreeNode<T> {
    T value;
    List<TreeNode<T>> children;

    public TreeNode(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public T getValue() {
        return value;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }
}

interface NodeVisitor<T, R> {
    R visit(TreeNode<T> node);
}

class FamilyTreeUtils {
    // Pre-order traversal with visitor, combine results according to visitor logic
    public static <T, R> R traverse(TreeNode<T> root, NodeVisitor<T, R> visitor) {
        return visitor.visit(root);
    }

    // Flatten tree to list of values in pre-order
    public static <T> List<T> flatten(TreeNode<T> root) {
        List<T> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private static <T> void preOrder(TreeNode<T> node, List<T> res) {
        if (node == null) return;
        res.add(node.getValue());
        for (TreeNode<T> child : node.getChildren()) {
            preOrder(child, res);
        }
    }
}

// Visitor đếm tổng số người
class CountPersonVisitor implements NodeVisitor<Person, Integer> {
    @Override
    public Integer visit(TreeNode<Person> node) {
        int count = 1; // đếm node hiện tại
        for (TreeNode<Person> child : node.getChildren()) {
            count += visit(child);
        }
        return count;
    }
}

// Visitor tìm người lớn tuổi nhất
class OldestPersonVisitor implements NodeVisitor<Person, Person> {
    @Override
    public Person visit(TreeNode<Person> node) {
        Person oldest = node.getValue();
        for (TreeNode<Person> child : node.getChildren()) {
            Person childOldest = visit(child);
            if (childOldest.getAge() > oldest.getAge()) {
                oldest = childOldest;
            }
        }
        return oldest;
    }
}

// Visitor liệt kê danh sách tên theo pre-order
class ListNamesVisitor implements NodeVisitor<Person, List<String>> {
    @Override
    public List<String> visit(TreeNode<Person> node) {
        List<String> res = new ArrayList<>();
        res.add(node.getValue().getName());
        for (TreeNode<Person> child : node.getChildren()) {
            res.addAll(visit(child));
        }
        return res;
    }
}

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
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            TreeNode<Person> root = null;
            Map<Integer, TreeNode<Person>> nodes = new HashMap<>();
            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;

                String[] parts = line.split(" ");

                switch (parts[0]) {
                    case "BuildFamilyTree":
                        int n = Integer.parseInt(parts[1]);
                        // Mỗi người gồm: name age k con_1 con_2 ... con_k
                        // chỉ số con đánh từ 1, index người cũng 1-based
                        // Tạo nodes trước
                        for (int i = 1; i <= n; i++) {
                            String name = parts[2 + (i-1)* (3 + Integer.parseInt(parts[2 + (i-1)* (3 + 0)]))];
                            // Do độ dài không cố định, xử lý sau
                            // Cách khác: duyệt từng người:
                        }
                        // Vì cấu trúc mỗi người có 3 + k phần tử:
                        // name (1), age (2), k (3), k con
                        // bắt đầu ở parts[2]
                        // Duyệt người lần lượt
                        nodes.clear();
                        int index = 2;
                        for (int i = 1; i <= n; i++) {
                            String name = parts[index++];
                            int age = Integer.parseInt(parts[index++]);
                            int k = Integer.parseInt(parts[index++]);
                            Person p = new Person(name, age);
                            TreeNode<Person> node = new TreeNode<>(p);
                            nodes.put(i, node);
                            // Bỏ qua phần con, xử lý sau
                            index += k;
                        }
                        // Lấy lại index để liên kết con
                        index = 2;
                        for (int i = 1; i <= n; i++) {
                            // name age k
                            index++; // name
                            index++; // age
                            int k = Integer.parseInt(parts[index++]);
                            TreeNode<Person> parent = nodes.get(i);
                            for (int j = 0; j < k; j++) {
                                int childIdx = Integer.parseInt(parts[index++]);
                                TreeNode<Person> child = nodes.get(childIdx);
                                parent.addChild(child);
                            }
                        }
                        root = nodes.get(1); // người đầu tiên là root
                        break;

                    case "CountPerson":
                        CountPersonVisitor countVisitor = new CountPersonVisitor();
                        int count = root == null ? 0 : FamilyTreeUtils.traverse(root, countVisitor);
                        System.out.println(count);
                        break;

                    case "OldestPerson":
                        OldestPersonVisitor oldestVisitor = new OldestPersonVisitor();
                        if (root == null) {
                            System.out.println("Not found");
                        } else {
                            Person oldest = FamilyTreeUtils.traverse(root, oldestVisitor);
                            System.out.println(oldest);
                        }
                        break;

                    case "ListNames":
                        ListNamesVisitor namesVisitor = new ListNamesVisitor();
                        if (root == null) {
                            System.out.println();
                        } else {
                            List<String> names = FamilyTreeUtils.traverse(root, namesVisitor);
                            System.out.println(String.join(" ", names));
                        }
                        break;

                    default:
                        // Unknown command
                        break;
                }
            }
        }
        sc.close();
    }
}


