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
        List<Integer> Temp = new ArrayList<Integer>();
        int n = in.nextInt(); // the number of temperatures to analyse
        for (int i = 0; i < n; i++) {
            Temp.add(in.nextInt()); // a temperature expressed as an integer ranging from -273 to 5526
        }
        
        int MinValue = Temp.size() > 0 ? Temp.get(0) : 0;
        for(Integer i: Temp)
        {
            if(Math.abs(i) < Math.abs(MinValue))
                MinValue = i;
            else if (i + MinValue == 0)
                MinValue = Math.abs(i);
        }
        System.out.println(MinValue);
    }
}