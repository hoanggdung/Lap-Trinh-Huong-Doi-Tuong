// DATA_STRUCTURE001 - Hàng đợi ưu tiên 
import java.util.*;

class Student implements Comparable<Student> {
  int id; String name; double cgpa;
  Student(int i,String n,double c){ id=i; name=n; cgpa=c; }
  int getID(){ return id; }
  String getName(){ return name; }
  double getCGPA(){ return cgpa; }
  public int compareTo(Student o){
    if(cgpa!=o.cgpa) return Double.compare(o.cgpa,cgpa);
    if(!name.equals(o.name)) return name.compareTo(o.name);
    return Integer.compare(id,o.id);
  }
}

class Priorities {
  PriorityQueue<Student> q=new PriorityQueue<>();
  List<Student> getStudents(List<String> events){
    for(String e:events){
      String[] p=e.split(" ");
      if(p[0].equals("ENTER")) q.add(new Student(Integer.parseInt(p[3]),p[1],Double.parseDouble(p[2])));
      else if(p[0].equals("SERVED")) q.poll();
    }
    List<Student> r=new ArrayList<>();
    while(!q.isEmpty()) r.add(q.poll());
    return r;
  }
}

public class Main {
  public static void main(String[]a){
    Scanner s=new Scanner(System.in);
    int n=Integer.parseInt(s.nextLine());
    List<String> ev=new ArrayList<>();
    for(int i=0;i<n;i++) ev.add(s.nextLine());
    s.close();
    Priorities p=new Priorities();
    List<Student> st=p.getStudents(ev);
    if(st.isEmpty()) System.out.println("EMPTY");
    else for(Student x:st) System.out.println(x.getName());
  }
}
