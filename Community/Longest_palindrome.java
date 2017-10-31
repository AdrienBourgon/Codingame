import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        List<String> Res = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String s = in.next();
        
        int c = s.length();
        for(int l = c; Res.size() == 0 && l > 0; --l)
            for(int i = 0; i <= c - l; ++i)
                if(IsPalindrome(s, i, l))
                    Res.add(s.substring(i, i + l));
        
        System.out.println(String.join("\n", Res));
    }
    
    public static Boolean IsPalindrome(String s, int d, int l)
    {
        for(int i = 0, c = l / 2; i <= c; ++i)
            if(s.charAt(d + i) != s.charAt(d + l - i - 1))
                return false;
        return true;
    }
}