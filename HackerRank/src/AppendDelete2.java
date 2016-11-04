import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class AppendDelete2 {

    	public static void main(String[] args) {

	Scanner in = new Scanner(System.in);
    String s = in.next();
    String[] S = s.split(""); 
    String t = in.next();
    String[] T=t.split("");
    int k = in.nextInt();
    int delete = 0;
    int append=0;
    int large = 0;
    int small=0;
    if(s.length()!=0 && t.length()!=0){
    if(s.equals(t)){
        if(k>=((s.length()*2)+1)){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    } 
       else{
     if(S.length >= T.length)  {
         large = S.length;
         small = T.length;
     } else{
         large =T.length;
         small =S.length;
     }
     for(int i=0;i<large;i++){
         if(i<small){
             if(!S[i].equals(T[i])){
          // System.out.println(S[i]+ T[i]);
                 delete = large-i;
                 if(i==0) {
                     delete = delete+1;}
                 append=small-i;
                  break;
             }
         }
         if(i>=small){
             delete=large-i;
             break;
         }
     }  
      
        if((append+delete)<=k){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
                 
    }
    }
} 
}
