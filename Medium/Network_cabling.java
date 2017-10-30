import java.util.*;
import java.io.*;
import java.math.*;
import java.awt.Point;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        long SumY = 0, MinX = Long.MAX_VALUE, MaxX = Long.MIN_VALUE;
        
        List<House> Houses = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            long X = in.nextLong();
            long Y = in.nextLong();
            
            Houses.add(new House(X, Y));
            SumY += Y;
            if (X < MinX) MinX = X;
            if (X > MaxX) MaxX = X;
        }
        long Length = 0;
        
        Collections.sort(Houses, (H1, H2) -> { return Long.compare(H1.y, H2.y); });

        if(Houses.size() > 1)
        {
            long Med = Houses.get(Houses.size() / 2).y;
            
            Length = MaxX - MinX;
            
            for(House H: Houses)
                Length += Math.abs(H.y - Med);
        }
        
        System.out.println(Length);
    }
}
class House
{
    public long x, y;
    
    public House(long X, long Y)
    {
        this.x = X;
        this.y = Y;
    }
}