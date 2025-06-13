//OOP012 - Sách và tác giá 
import java.util.*;

class Author {
  String name,email;
  char gender;
  Author(String n,String e,char g){
    name=n; email=e;
    if(g=='f'||g=='m') gender=g;
    else throw new IllegalArgumentException("Gender must be 'f' or 'm'");
  }
  public String toString(){ return "Author[name="+name+", email="+email+", gender="+gender+"]"; }
}

class Book {
  String name;
  Author author;
  double price;
  int qty=0;
  Book(String n, Author a, double p){ name=n; author=a; price=p; }
  Book(String n, Author a, double p,int q){ name=n; author=a; price=p; qty=q; }
  public String toString(){
    return "Book[name="+name+", "+author+", price="+price+", qty="+qty+"]";
  }
}

public class Main {
  public static void main(String[]a){
    Scanner s=new Scanner(System.in);
    for(int t=Integer.parseInt(s.nextLine());t-->0;){
      if(s.nextLine().equals("Book")){
        String bn=s.nextLine();
        double p=Double.parseDouble(s.nextLine());
        int q=Integer.parseInt(s.nextLine());
        if(s.nextLine().equals("Author")){
          String an=s.nextLine();
          String em=s.nextLine();
          char g=s.nextLine().charAt(0);
          Author auth=new Author(an,em,g);
          Book book=new Book(bn,auth,p,q);
          System.out.println(book);
        }
      }
    }
    s.close();
  }
}
