 GENERIC027 Bộ Quản Lý Cặp Giá Trị Đa Năng 
import java.util.*;
import java.util.function.Predicate;

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
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
               Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

class PairManager<A, B> {
    private List<Pair<A, B>> pairs = new ArrayList<>();

    public void add(Pair<A, B> pair) {
        pairs.add(pair);
    }

    public void remove(Pair<A, B> pair) {
        pairs.remove(pair);
    }

    public List<Pair<A, B>> filter(Predicate<Pair<A, B>> predicate) {
        List<Pair<A, B>> res = new ArrayList<>();
        for (Pair<A, B> p : pairs) {
            if (predicate.test(p)) {
                res.add(p);
            }
        }
        return res;
    }

    public List<Pair<A, B>> sort(Comparator<Pair<A, B>> comparator) {
        List<Pair<A, B>> copy = new ArrayList<>(pairs);
        copy.sort(comparator);
        return copy;
    }

    public Map<A, List<B>> groupByFirst() {
        Map<A, List<B>> map = new LinkedHashMap<>();
        for (Pair<A, B> p : pairs) {
            map.computeIfAbsent(p.getFirst(), k -> new ArrayList<>()).add(p.getSecond());
        }
        return map;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            PairManager<String, Integer> manager = new PairManager<>();

            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;

                String[] parts = line.split(" ");
                String cmd = parts[0];

                switch (cmd) {
                    case "AddPairStringInt": {
                        String s = parts[1];
                        int x = Integer.parseInt(parts[2]);
                        manager.add(new Pair<>(s, x));
                        break;
                    }
                    case "RemovePairStringInt": {
                        String s = parts[1];
                        int x = Integer.parseInt(parts[2]);
                        manager.remove(new Pair<>(s, x));
                        break;
                    }
                    case "FilterPairFirstEquals": {
                        String s = parts[1];
                        List<Pair<String, Integer>> filtered = manager.filter(p -> p.getFirst().equals(s));
                        for (Pair<String, Integer> p : filtered) {
                            System.out.println(p);
                        }
                        break;
                    }
                    case "SortPairBySecondAsc": {
                        List<Pair<String, Integer>> sorted = manager.sort(Comparator.comparing(Pair::getSecond));
                        for (Pair<String, Integer> p : sorted) {
                            System.out.println(p);
                        }
                        break;
                    }
                    case "GroupByFirst": {
                        Map<String, List<Integer>> grouped = manager.groupByFirst();
                        for (Map.Entry<String, List<Integer>> entry : grouped.entrySet()) {
                            System.out.print(entry.getKey() + ":");
                            for (Integer val : entry.getValue()) {
                                System.out.print(" " + val);
                            }
                            System.out.println();
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


