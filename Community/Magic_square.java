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
        int n = in.nextInt();
        
        List<Integer> Nums = new ArrayList<>();
        Boolean Magic = true;
        int[] sL = new int[n], sC = new int[n], sD = new int[] {0, 0};
        for(int i = 0; i < n; ++i) { sL[i] = 0; sC[i] = 0; }
        
        first: for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int c = in.nextInt();
                
                if(c <= 0 || c > n*n || Nums.contains(c))
                {
                    Magic = false;
                    break first;
                }
                Nums.add(c);
                
                sL[i] += c;
                sC[j] += c;
                
                if(i == j) sD[0] += c;
                if(j == n - 1 - i) sD[1] += c;
            }
        }
        
        int ssL = 0, ssC = 0;
        for(int c: sL) ssL += c;
        for(int c: sC) ssC += c;
        
        if(Magic)
            Magic = ssL == ssC;
        
        if(Magic)
            if (sD[0] != ssL / n || sD[1] != ssL / n)
                Magic = false;
                
        System.out.println(Magic ? "MAGIC" : "MUGGLE");
    }
}