import java.math.BigInteger;
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        while(n>0){
            n--;
            String s= sc.nextLine();
            String[] word=s.split("\\s+");
            BigInteger a=new BigInteger(word[0]);
            BigInteger b=new BigInteger(word[2]);
            if(word[1].equals("+")){
                System.out.println(a.add(b));
            }else{
                if(word[1].equals("-")){
                    System.out.println(a.subtract(b));
                }else{
                    System.out.println(a.multiply(b));
                }
            }
        }
    }
}