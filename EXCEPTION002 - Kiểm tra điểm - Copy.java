// EXCEPTION002 - Kiểm tra điểm 
import java.util.Scanner;
class NotJavaFileException extends Exception {
    NotJavaFileException(String m){ super(m); }
}
public class Main {
    static int checkFileExtension(String f) throws NotJavaFileException {
        if (f == null || f.trim().isEmpty())
            throw new NotJavaFileException("Not java file exception.Mark is -1");
        return f.endsWith(".java") ? 1 : 0;
    }
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        for (int i = 0; i < t; i++) {
            String f = s.nextLine();
            try {
                System.out.println(checkFileExtension(f));
            } catch (NotJavaFileException e) {
                System.out.println(e.getMessage());
            }
        }
        s.close();
    }
}



