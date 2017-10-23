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
        long C = in.nextInt();
        long Total = 0;
        List<Long> Oods = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int B = in.nextInt();
            Total += B;
            Oods.add((long)B);
        }
        
        if (Total >= C)
        {
            Collections.sort(Oods);
            
            List<Long> Contributions = new ArrayList<>();
            Long Avg = (long)0;
            
            for(Long L: Oods)
            {
                Avg = C / (N - Contributions.size());
                Contributions.add(L > Avg ? Avg : L);
                C -= Contributions.get(Contributions.size() - 1);                
            }
            
            for(Long L: Contributions) System.out.println(L);
        }
        else
            System.out.println("IMPOSSIBLE");
    }
}