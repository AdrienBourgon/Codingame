import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        
        for(int i = 0; i < 2 * N; ++i)
            System.out.println(DrawLine(N, i));
            
    }
    
    public static String DrawLine(int N, int i)
    {
        String S = i == 0 ? "." : "";
        
        if(i < N)
        {
            int nChar = 2 * N - i - 1;
            if (i == 0) --nChar;
            S += N > 1 ? String.format("%" + nChar + "s", "") : "";
            for(int j = 0; j < (2 * i + 1); ++j) S += "*";
        }
        else
        {
            i -= N;
            S += i < (N - 1) ? String.format("%" + (N - i - 1) + "s", "") : "";
            for(int j = 0; j < (2 * i + 1); ++j) S += "*";
            S += String.format("%" + (2 * (N - i) - 1) + "s", "");
            for(int j = 0; j < (2 * i + 1); ++j) S += "*";
        }
        
        return S;
    }
}