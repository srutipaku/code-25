/*
 * You have a string, , of lowercase English alphabetic letters. You can perform two types of operations on :

Append a lowercase English alphabetic letter to the end of the string.
Delete the last character in the string. Performing this operation on an empty string results in an empty string.
Given an integer, , and two strings,  and , determine whether or not you can convert  to  by performing exactly  of the above operations on . If it's possible, print Yes; otherwise, print No.

Input Format

The first line contains a string, , denoting the initial string. 
The second line contains a string, , denoting the desired final string. The third line contains an integer, , denoting the desired number of operations.

Constraints

 and  consist of lowercase English alphabetic letters.
Output Format

Print Yes if you can obtain string  by performing exactly  operations on ; otherwise, print No.

Sample Input 0

hackerhappy
hackerrank
9
Sample Output 0

Yes
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class AppendAndDelete {

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
            int n=0;
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
                         delete = i;
                          break;
                     }else {
                         delete =i;
                     }
                 }
                 if(i>=small){
                     delete=i;
                     break;
                 }
             }  
        
            append = 2*delete;
           // System.out.println(delete);
            n = k + append - s.length()-t.length();
         //   System.out.println(n);
            
                if(n>=append ||n%2==0){
                    System.out.println("Yes");
                }else {
                System.out.println("No");
            }     
                         
            }
}