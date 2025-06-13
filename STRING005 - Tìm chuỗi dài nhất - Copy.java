import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Đọc số lượng test case
        int t = Integer.parseInt(scanner.nextLine().trim());
        
        // Lặp qua từng test case
        for (int i = 0; i < t; i++) {
            // Đọc chuỗi (câu) vào
            String sentence = scanner.nextLine().trim();
            
            // Nếu câu trống, in ra chuỗi trống
            if (sentence.isEmpty()) {
                System.out.println("");
                continue;
            }

            // Tách chuỗi thành các từ
            String[] words = sentence.split("\\s+");

            // Biến để lưu từ dài nhất
            String longestWord = "";
            
            // Duyệt qua các từ để tìm từ dài nhất
            for (String word : words) {
                if (word.length() >= longestWord.length()) {
                    longestWord = word;  // Chọn từ có chiều dài lớn nhất (hoặc từ sau cùng nếu cùng chiều dài)
                }
            }
            
            // In ra từ dài nhất
            System.out.println(longestWord);
        }

        scanner.close();
    }
}