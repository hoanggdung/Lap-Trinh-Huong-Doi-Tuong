// INHERITANCE012 - Quản lí nhân viên 
import java.util.Scanner;
class Employee {
    String n; double s;
    Employee() {}
    Employee(String n,double s){this.n=n;this.s=s;}
    double calcSalary(){return s;}
    String getInfo(){return "Họ tên: "+n;}
}
class FullTimeEmployee extends Employee {
    double b;
    FullTimeEmployee(String n,double s,double b){super(n,s);this.b=b;}
    @Override double calcSalary(){return s+b;}
    @Override String getInfo(){
        return "Loại: FullTime\nHọ tên: "+n+
            "\nLương cơ bản: "+String.format("%.1f",s)+
            "\nThưởng: "+String.format("%.1f",b)+
            "\n=> Lương thực nhận: "+String.format("%.1f",calcSalary());
    }
}
class PartTimeEmployee extends Employee {
    int h; double r;
    PartTimeEmployee(String n,int h,double r){super(n,0);this.h=h;this.r=r;}
    @Override double calcSalary(){return h*r;}
    @Override String getInfo(){
        return "Loại: PartTime\nHọ tên: "+n+
            "\nSố giờ làm việc: "+h+" giờ"+
            "\nTiền công mỗi giờ: "+String.format("%.1f",r)+
            "\n=> Lương thực nhận: "+String.format("%.1f",calcSalary());
    }
}
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String t1=sc.nextLine().trim(),n1=sc.nextLine().trim(),
               l3=sc.nextLine().trim(),l4=sc.nextLine().trim(),
               t2=sc.nextLine().trim(),n2=sc.nextLine().trim(),
               l6=sc.nextLine().trim(),l7=sc.nextLine().trim();
        Employee e1,e2;
        if(t1.equalsIgnoreCase("FullTime"))
            e1=new FullTimeEmployee(n1,Double.parseDouble(l3),Double.parseDouble(l4));
        else e1=new PartTimeEmployee(n1,Integer.parseInt(l3),Double.parseDouble(l4));
        if(t2.equalsIgnoreCase("FullTime"))
            e2=new FullTimeEmployee(n2,Double.parseDouble(l6),Double.parseDouble(l7));
        else e2=new PartTimeEmployee(n2,Integer.parseInt(l6),Double.parseDouble(l7));
        System.out.println("--- Thông tin nhân viên ---");
        System.out.println(e1.getInfo()+"\n");
        System.out.println(e2.getInfo());
        sc.close();
    }
}


