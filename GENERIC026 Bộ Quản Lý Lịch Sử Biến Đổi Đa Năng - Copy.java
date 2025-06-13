// GENERIC026 Bộ Quản Lý Lịch Sử Biến Đổi Đa Năng 
 import java.util.*;

interface Operation<T> {
    List<T> apply(List<T> input);
    List<T> undo(List<T> input);
}

class HistoryManager<T> {
    private Stack<List<T>> undoStack = new Stack<>();
    private Stack<List<T>> redoStack = new Stack<>();
    private List<T> current = new ArrayList<>();

    public void init(List<T> initial) {
        current = new ArrayList<>(initial);
        undoStack.clear();
        redoStack.clear();
    }

    public void applyOperation(Operation<T> op) {
        undoStack.push(new ArrayList<>(current));
        current = op.apply(new ArrayList<>(current));
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new ArrayList<>(current));
            current = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new ArrayList<>(current));
            current = redoStack.pop();
        }
    }

    public List<T> getCurrent() {
        return current;
    }
}

class ReverseOperation<T> implements Operation<T> {
    @Override
    public List<T> apply(List<T> input) {
        Collections.reverse(input);
        return input;
    }

    @Override
    public List<T> undo(List<T> input) {
        Collections.reverse(input);
        return input;
    }
}

class SortOperation<T extends Comparable<T>> implements Operation<T> {
    private List<T> beforeSort;

    @Override
    public List<T> apply(List<T> input) {
        beforeSort = new ArrayList<>(input);
        Collections.sort(input);
        return input;
    }

    @Override
    public List<T> undo(List<T> input) {
        return new ArrayList<>(beforeSort);
    }
}

class AddElementOperation<T> implements Operation<T> {
    private T element;

    public AddElementOperation(T element) {
        this.element = element;
    }

    @Override
    public List<T> apply(List<T> input) {
        input.add(element);
        return input;
    }

    @Override
    public List<T> undo(List<T> input) {
        if (!input.isEmpty()) input.remove(input.size() - 1);
        return input;
    }
}

class RemoveElementOperation<T> implements Operation<T> {
    private T removedElement;

    @Override
    public List<T> apply(List<T> input) {
        if (!input.isEmpty()) {
            removedElement = input.remove(input.size() - 1);
        } else {
            removedElement = null;
        }
        return input;
    }

    @Override
    public List<T> undo(List<T> input) {
        if (removedElement != null) input.add(removedElement);
        return input;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        HistoryManager<Integer> manager = new HistoryManager<>();

        while (t-- > 0) {
            while (true) {
                String line = sc.nextLine().trim();
                if (line.equals("End")) break;
                String[] parts = line.split(" ");

                String cmd = parts[0];
                if (cmd.equals("InitIntList")) {
                    int n = Integer.parseInt(parts[1]);
                    List<Integer> initList = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        initList.add(Integer.parseInt(parts[2 + i]));
                    }
                    manager.init(initList);
                } else if (cmd.equals("Reverse")) {
                    manager.applyOperation(new ReverseOperation<>());
                } else if (cmd.equals("Sort")) {
                    manager.applyOperation(new SortOperation<>());
                } else if (cmd.equals("Add")) {
                    int valToAdd = Integer.parseInt(parts[1]);
                    manager.applyOperation(new AddElementOperation<>(valToAdd));
                } else if (cmd.equals("Remove")) {
                    manager.applyOperation(new RemoveElementOperation<>());
                } else if (cmd.equals("Undo")) {
                    manager.undo();
                } else if (cmd.equals("Redo")) {
                    manager.redo();
                } else if (cmd.equals("Print")) {
                    List<Integer> cur = manager.getCurrent();
                    if (cur.isEmpty()) {
                        System.out.println();
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < cur.size(); i++) {
                            if (i > 0) sb.append(" ");
                            sb.append(cur.get(i));
                        }
                        System.out.println(sb);
                    }
                }
            }
        }
        sc.close();
    }
}


