//EXCEPTION006 - Nguyên âm
import java.util.*;

class NotContainVowelException extends Exception {
  NotContainVowelException(String m){ super(m); }
}

public class Main {
  public static void main(String[]a){
    Scanner s=new Scanner(System.in);
    for(int N=Integer.parseInt(s.nextLine());N-->0;){
      String in=s.nextLine();
      try {
        if(!containsVowel(in)) throw new NotContainVowelException("String not contain vowels");
        System.out.println("String has vowels");
      } catch(NotContainVowelException e){
        System.out.println(e.getMessage());
      }
    }
    s.close();
  }
  
  static boolean containsVowel(String str){
    return str.toLowerCase().matches(".*[aeiou].*");
  }
}

