/*
 * Your submission will run against only preliminary test cases. Full test cases will run at the end of the day.
Consider two sets of positive integers,  and . We say that a positive integer, , is between sets  and  if the following conditions are satisfied:

All elements in  are factors of .
 is a factor of all elements in .
Given  and , find and print the number of integers (i.e., possible 's) that are between the two sets.

Input Format

The first line contains two space-separated integers describing the respective values of  (the number of elements in set ) and  (the number of elements in set ). 
The second line contains  distinct space-separated integers describing . 
The third line contains  distinct space-separated integers describing .

Constraints

Output Format

Print the number of integers that are considered to be between  and .

Sample Input

2 3
2 4
16 32 96
Sample Output

3
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BetweenTwoSets {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] b = new int[m];
        for(int b_i=0; b_i < m; b_i++){
            b[b_i] = in.nextInt();
        }
        int max_b = Integer.MIN_VALUE;
        int min_b = Integer.MAX_VALUE;
        int max_a = Integer.MIN_VALUE;
        int min_a = Integer.MAX_VALUE;
        int max=0;
        int min=0;
        int count=0;
        boolean boolean_a = false;
        boolean boolean_b = false;
        for(int i_a=0;i_a<a.length;i_a++){
            if(a[i_a]>max_a){
                max_a = a[i_a];
            }
            if(a[i_a]<min_a){
                min_a =a[i_a];
            }
        }
          for(int i_b=0;i_b<b.length;i_b++){
            if(b[i_b]>max_b){
                max_b = b[i_b];
            }
            if(b[i_b]<min_b){
                min_b =b[i_b];
            }
              
        }
        if(min_b<=min_a){
            min = min_b;
        }else{
            min=min_a;
        }
         if(max_b>=max_a){
            max = max_b;
        }else{
            max=max_a;
        }
        
        for(int i=min;i<=max;i++){
            for(int a_i=0;a_i<a.length;a_i++){
                if(i%a[a_i]!=0){
                    boolean_a = false;
                    break;
                }else {
                    boolean_a = true;
                }
            }
            if(boolean_a){
                for(int b_i=0;b_i<b.length;b_i++){
                    if(b[b_i]%i!=0){
                        boolean_b=false;
                        break;
                    }else{
                        boolean_b=true;
                    }
                }
            }
            
            if(boolean_b && boolean_a){
                count++;
            }
            
        }
        
       System.out.println(count); 
	}
}

