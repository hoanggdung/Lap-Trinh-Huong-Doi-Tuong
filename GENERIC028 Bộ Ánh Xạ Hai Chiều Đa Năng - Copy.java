// GENERIC028 Bộ Ánh Xạ Hai Chiều Đa Năng {
import java.util.*;
import java.util.Optional;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() { return first; }
    public B getSecond() { return second; }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(first, p.first) && Objects.equals(second, p.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

class BiMap<A, B> {
    // Dùng LinkedHashMap để giữ thứ tự thêm vào
    private Map<A, B> forward = new LinkedHashMap<>();
    private Map<B, A> backward = new LinkedHashMap<>();

    public boolean put(A a, B b) {
        if (forward.containsKey(a) || backward.containsKey(b)) {
            // Nếu a hoặc b đã tồn tại trong ánh xạ thì trả về false
            return false;
        }
        forward.put(a, b);
        backward.put(b, a);
        return true;
    }

    public boolean removeByA(A a) {
        if (!forward.containsKey(a)) return false;
        B b = forward.remove(a);
        backward.remove(b);
        return true;
    }

    public boolean removeByB(B b) {
        if (!backward.containsKey(b)) return false;
        A a = backward.remove(b);
        forward.remove(a);
        return true;
    }

    public Optional<B> getByA(A a) {
        return Optional.ofNullable(forward.get(a));
    }

    public Optional<A> getByB(B b) {
        return Optional.ofNullable(backward.get(b));
    }

    public Set<A> keySet() {
        return forward.keySet();
    }

    public Set<B> valueSet() {
        return backward.keySet();
    }

    public Set<Pair<A, B>> entrySet() {
        LinkedHashSet<Pair<A, B>> set = new LinkedHashSet<>();
        for (Map.Entry<A, B> entry : forward.entrySet()) {
            set.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        return set;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            BiMap<String, String> biMap = new BiMap<>();

            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;
                String[] parts = line.split(" ", 3);

                String cmd = parts[0];

                switch (cmd) {
                    case "Put": {
                        // "Put a b" với b có thể là số hoặc chuỗi, ta xử lý như String bình thường
                        String a = parts[1];
                        String b = parts[2];
                        System.out.println(biMap.put(a, b));
                        break;
                    }
                    case "RemoveByA": {
                        String a = parts[1];
                        System.out.println(biMap.removeByA(a));
                        break;
                    }
                    case "RemoveByB": {
                        String b = parts[1];
                        System.out.println(biMap.removeByB(b));
                        break;
                    }
                    case "GetByA": {
                        String a = parts[1];
                        Optional<String> val = biMap.getByA(a);
                        System.out.println(val.orElse("None"));
                        break;
                    }
                    case "GetByB": {
                        String b = parts[1];
                        Optional<String> val = biMap.getByB(b);
                        System.out.println(val.orElse("None"));
                        break;
                    }
                    case "KeySet": {
                        for (String key : biMap.keySet()) {
                            System.out.println(key);
                        }
                        break;
                    }
                    case "ValueSet": {
                        for (String val : biMap.valueSet()) {
                            System.out.println(val);
                        }
                        break;
                    }
                    case "EntrySet": {
                        for (Pair<String, String> p : biMap.entrySet()) {
                            System.out.println(p);
                        }
                        break;
                    }
                    default:
                        // Ignore unknown commands
                        break;
                }
            }
        }

        sc.close();
    }
}


