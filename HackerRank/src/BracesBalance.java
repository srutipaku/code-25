import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BracesBalance {
    
    static boolean isValid(String s){
        String symbol = s;
        Stack<Character> st = new Stack<Character>();
        for(int i=0;i<symbol.length();i++){
            if(symbol.charAt(i)==')'){
                if(!st.isEmpty() && st.peek()=='(')
                    {
                   st.pop();
                }else{
                    return false;
                }
            }
            else if(symbol.charAt(i)=='}'){
                if(!st.isEmpty() && st.peek()=='{')
                    {
                    st.pop();
                }
                else{
                    return false;
                }
        }
           else if(symbol.charAt(i)==']'){
                if(!st.isEmpty() && st.peek()=='[')
                    {
                    st.pop();
                }
                else{
                    return false;
                }
        
    }
            else{
                st.push(symbol.charAt(i));
            }
        }
        if(st.isEmpty()){
            return true;
        }else{
            return false;
        }
        
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String s = in.next();
            boolean res =  BracesBalance.isValid(s);
            if(res){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
        
    }
}
