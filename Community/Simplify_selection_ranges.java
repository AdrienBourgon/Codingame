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
        String[] N = in.nextLine().replace("[", "").replace("]", "").split(",");
        
        int[] Nums = new int[N.length];
        for(int i = 0; i < N.length; ++i) Nums[i] = Integer.valueOf(N[i]);
        Arrays.sort(Nums);

        List<String> R = new ArrayList<>();
        
        for(int i = 0; i < Nums.length; ++i)
        {
            int j = i + 1;
            while (j < Nums.length)
            {
                if(Nums[i] != Nums[j] - j + i) break;
                ++j;
            }
            
            if(j - i > 2)
            {
                R.add(Nums[i] + "-" + Nums[j - 1]);
                i = j - 1;
            }
            else
                R.add(""+Nums[i]);
        }
        
        System.out.println(String.join(",", R));
    }
}