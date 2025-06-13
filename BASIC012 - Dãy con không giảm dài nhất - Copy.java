// BASIC012 - Dãy con không giảm dài nhất 
import java.util.Scanner;

public class Main {
  static int longestNonDecreasingSubsequence(int[] a){
    int max=1, cur=1;
    for(int i=1;i<a.length;i++){
      if(a[i]>=a[i-1]) cur++;
      else cur=1;
      if(cur>max) max=cur;
    }
    return a.length==0?0:max;
  }
  public static void main(String[] args){
    Scanner s=new Scanner(System.in);
    int t=Integer.parseInt(s.nextLine());
    while(t-->0){
      if(!s.hasNextLine()){
        System.out.println(0);
        continue;
      }
      String line=s.nextLine().trim();
      if(line.isEmpty()){
        System.out.println(0);
        continue;
      }
      String[] tok=line.split("\\s+");
      int[] arr=new int[tok.length];
      for(int i=0;i<tok.length;i++) arr[i]=Integer.parseInt(tok[i]);
      System.out.println(longestNonDecreasingSubsequence(arr));
    }
    s.close();
  }
}
