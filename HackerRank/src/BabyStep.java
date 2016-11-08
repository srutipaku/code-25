/*
 * Submissions will no longer be placed on the leaderboard. You may still attempt this problem for practice.
You are standing at point  on an infinite plane. In one step, you can move from some point  to any point  as long as the Euclidean distance, , between the two points is either or . In other words, each step you take must be exactly  or  in length.

You are given  queries in the form of , , and . For each query, print the minimum number of steps it takes to get from point  to point  on a new line.

Input Format

The first line contains an integer, , denoting the number of queries you must process. 
Each of the  subsequent lines contains three space-separated integers describing the respective values of , , and  for a query.
 */
import java.io.*;
import java.util.*;
public class BabyStep {
	public static int steps(int a, int b, int d) {
        int minSteps = d/b;
        if (d % b == 0) {
            return d / b;
        }
        
        if (d % a == 0 && d / a <= minSteps + 1) {
            return d / a;
        }
        
        if (minSteps != 0) {
            return d / b + 1;
        } else {
            return d / b + 2;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while(q > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();
            
            System.out.println(steps(a,b,d));
            
            q--;
        }
    }
}
