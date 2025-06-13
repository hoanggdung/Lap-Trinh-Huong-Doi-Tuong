 //INHERITANCE013 - Quản lí giảng viên 
 import java.util.Scanner;
class Teacher {
    String n; double b;
    Teacher(String n,double b){this.n=n;this.b=b;}
    double calcSalary(){return b;}
    String getInfo(){return "Họ tên: "+n;}
}
class PermanentLecturer extends Teacher {
    double r;
    PermanentLecturer(String n,double b,double r){super(n,b);this.r=r;}
    @Override double calcSalary(){return b+r;}
    @Override String getInfo(){
        return "Loại giảng viên: Permanent\nHọ tên: "+n+"\nLương thực nhận: "+String.format("%.1f",calcSalary());
    }
}
class VisitingLecturer extends Teacher {
    int h; double p;
    VisitingLecturer(String n,int h,double p){super(n,0);this.h=h;this.p=p;}
    @Override double calcSalary(){return h*p;}
    @Override String getInfo(){
        return "Loại giảng viên: Visiting\nHọ tên: "+n+"\nLương thực nhận: "+String.format("%.1f",calcSalary());
    }
}
public class Main {
    public static void main(String[] a){
        Scanner sc=new Scanner(System.in);
        String t1=sc.nextLine().trim(),n1=sc.nextLine().trim(),l3=sc.nextLine().trim(),l4=sc.nextLine().trim(),
               t2=sc.nextLine().trim(),n2=sc.nextLine().trim(),l6=sc.nextLine().trim(),l7=sc.nextLine().trim();
        Teacher te1,te2;
        if(t1.equalsIgnoreCase("PermanentLecturer")) te1=new PermanentLecturer(n1,Double.parseDouble(l3),Double.parseDouble(l4));
        else te1=new VisitingLecturer(n1,Integer.parseInt(l3),Double.parseDouble(l4));
        if(t2.equalsIgnoreCase("PermanentLecturer")) te2=new PermanentLecturer(n2,Double.parseDouble(l6),Double.parseDouble(l7));
        else te2=new VisitingLecturer(n2,Integer.parseInt(l6),Double.parseDouble(l7));
        System.out.println("--- Thông tin giảng viên ---");
        System.out.println(te1.getInfo());
        System.out.println(te2.getInfo());
        sc.close();
    }
}


