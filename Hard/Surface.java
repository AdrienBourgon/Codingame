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
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        int[][] Grid = new int[H][];
        
        if (in.hasNextLine()) in.nextLine();
        for (int i = 0; i < H; i++) {
            int[] Row = new int[L];
            String S = in.nextLine();
            for(int j = 0; j < S.length(); ++j)
                Row[j] = S.charAt(j) == 'O' ? i*H+j+1 : 0;
            Grid[i] = Row;
        }
        
        //Flood fill
        Queue<Point> Pile = new LinkedList<>();
        
        for(int i = 0; i < H; ++i)
        for(int j = 0; j < L; ++j)
        {
            int V = Grid[i][j];
            if (V == 0) continue;
            Boolean first = true;
            
            Pile.add(new Point(i, j));
            while(!Pile.isEmpty())
            {
                Point P = Pile.poll();
                int x = P.x, y = P.y;
                if(Grid[x][y] == 0 || !first && Grid[x][y] == V) continue;
                Grid[x][y] = V;
                first = false;
                
                if(x > 0 && Grid[x - 1][y] != 0) Pile.add(new Point(x - 1, y)); // Up
                if(x < H - 1 && Grid[x + 1][y] != 0) Pile.add(new Point(x + 1, y)); // Down
                if(y > 0 && Grid[x][y - 1] != 0) Pile.add(new Point(x, y - 1)); // Left
                if(y < L - 1 && Grid[x][y + 1] != 0) Pile.add(new Point(x, y + 1)); // Right
            }
        }
		
        int N = in.nextInt();
        first: for (int i = 0; i < N; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            
            int n = 0;
            if (Grid[Y][X] != 0) //Looking for all the cases with the same value
            for(int j = 0; j < H; ++j)
            for(int k = 0; k < L; ++k)
            if(Grid[j][k] == Grid[Y][X])
                ++n;
            
            System.out.println(n);
        }
    }
}