import java.util.Scanner;

public class Main {

    static class Song {
        String name, author;
        int duration;

        Song(String name, String author, int duration) {
            this.name = name;
            this.author = author;
            this.duration = duration;
        }

        boolean equals(Song other) {
            if (other == null) return false;
            return name.equals(other.name) &&
                   author.equals(other.author) &&
                   duration == other.duration;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] l1 = scanner.nextLine().trim().split("\\s+");
            Song a = new Song(l1[0], l1[1], Integer.parseInt(l1[2]));

            String[] l2 = scanner.nextLine().trim().split("\\s+");
            Song b = new Song(l2[0], l2[1], Integer.parseInt(l2[2]));

            System.out.println(a.equals(b));
        }

        scanner.close();
    }
}
