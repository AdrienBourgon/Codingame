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
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[] T = in.nextLine().toLowerCase().toCharArray();
        List<String> Letters = new ArrayList<>();
        for (int i = 0; i < H; i++)
            Letters.add(in.nextLine());
        
        String res = "";
        for(int i = 0; i < H; ++i)
        {
            for(char c: T)
            {
                int Start = (int)c - (int)'a';
                if (Start < 0 || Start > 26) Start = 26;
                Start *= L;
                
                res += Letters.get(i).substring(Start, Start + L);
            }
            res += "\n";
        }

        System.out.println(res);
    }
}