// STRING006 - Chữ cái đầu tiên 
import java.util.Scanner;
public class Main{
  public static void main(String[]a){
    Scanner s=new Scanner(System.in);
    int n=Integer.parseInt(s.nextLine());
    while(n-->0) System.out.println("My initials are: "+extractUppercase(s.nextLine()));
    s.close();
  }
  static String extractUppercase(String str){
    StringBuilder r=new StringBuilder();
    for(char c:str.toCharArray()) if(Character.isUpperCase(c)) r.append(c);
    return r.toString();
  }
}
