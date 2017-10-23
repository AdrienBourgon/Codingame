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
        
        List<Integer> Values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Values.add(in.nextInt());
        }

        Integer BiggestLoss = 0;
        
        for(int i = 0, c = Values.size(); i < c; ++i)
        {
            Integer j = i + 1, Loss = 0;
            while(j < c && Values.get(j) <= Values.get(i))
            {
                if ((Values.get(j) - Values.get(i)) < BiggestLoss)
                    BiggestLoss = Values.get(j) - Values.get(i);
                ++j;
            }
            i = j - 1;
        }
        
        System.out.println(BiggestLoss);
    }
}