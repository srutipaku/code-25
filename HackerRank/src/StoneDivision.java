/*
 * Consider the following game:

There are two players, First and Second, sitting in front of a pile of  stones. First always plays first.
There is a set, , of  distinct integers defined as .
The players move in alternating turns. During each turn, a player chooses some  and splits one of the piles into exactly  smaller piles of equal size. If no  exists that will split one of the available piles into exactly equal smaller piles, the player loses.
Both players always play optimally.
Given , , and the contents of , find and print the winner of the game. If First wins, print First; otherwise, print Second.

Input Format

The first line contains two space-separated integers describing the respective values of  (the size of the initial pile) and  (the size of the set). 
The second line contains  distinct space-separated integers describing the respective values of .

Constraints

Output Format

Print First if the First player wins the game; otherwise, print Second.
 */
import java.io.*;
import java.util.*;

public class StoneDivision{

  
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        long[] s = new long[m];
        for (int i = 0; i < m; i++)
            s[i] = sc.nextLong();
        for (int i = 0; i < m; i++) {
            if (n%s[i]==0&&s[i]%2==0) {
                System.out.println("First");
                return;
            }
        }
        HashMap<Long, Boolean> tried = new HashMap<Long, Boolean>();
        System.out.println(canWin(n,s,tried)?"First":"Second");
    }
    
    public static boolean canWin(long n, long[] s, HashMap<Long, Boolean> tried) {
        if (tried.containsKey(n))
            return tried.get(n);
        for (long i : s) {
            if (n%i==0) {
                if (!canWin(n/i, s, tried)) {
                    tried.put(n, true);
                    return true;
                }
            }
        }
        tried.put(n, false);
        return false;
    }
    }
