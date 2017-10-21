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
        
        List<Integer> Puissances = new ArrayList<Integer>();
        
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            Puissances.add(in.nextInt());
        }
        
        Collections.sort(Puissances);
        
        Integer Min = N > 1 ? Puissances.get(1) - Puissances.get(0) : 0;
        for(int i = 2; i < N; ++i)
            if(Puissances.get(i) - Puissances.get(i-1) < Min)
                Min = Puissances.get(i) - Puissances.get(i-1);
        

        System.out.println(Min);
    }
}