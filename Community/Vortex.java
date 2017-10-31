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
        List<int[]> Matrice = new ArrayList<>();
        
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        long x = in.nextLong();
        for (int i = 0; i < H; i++) {
            int[] Line = new int[W];
            for (int j = 0; j < W; j++) {
                Line[j] = in.nextInt();
            }
            Matrice.add(Line);
        }
        
        int[][] NouvelleMatrice = new int[H][]; //Création de la nouvelle matrice
        for(int i = 0; i < H; ++i)
            NouvelleMatrice[i] = new int[W];

        //Calcul des coordonnées des données et remplissage de la nouvelle matrice
        for(int i = 0; i < H; ++i)
            for(int j = 0; j < W; ++j)
            {
                int mod = (2*(W+H - 4*Math.min(Math.min(i, j), Math.min(W - j - 1, H - i - 1)) - 2));
                
                Point P = new Point(i, j);
                if (mod != 0)
                {
                    int d = (int)(x % mod);
                        
                    P = NouvelEmplacement(i, j, d, W, H);
                }
                
                NouvelleMatrice[P.x][P.y] = Matrice.get(i)[j];
            }
        for(int i = 0; i < H; ++i)
        {
            for(int j = 0; j < W; ++j)
                System.out.print((j > 0 ? " " : "") + NouvelleMatrice[i][j]);
            System.out.println();
        }
    }
    
    public static Point NouvelEmplacement(int x, int y, int d, int W, int H)
    {        
        int m = Math.min(Math.min(x, y), Math.min(W - y - 1, H - x - 1));
        Point P = new Point(x, y);
        
        if(W % 2 == 1 && H % 2 == 1)
        {
            int delta = Math.min(W, H) / 2;
            
            if(W == H && x == H / 2 && y == W / 2) return P;
            if(W < H && y == W / 2 && x > H / 2 - delta && x < H / 2 + delta) return P;
            if(W > H && x == H / 2 && y > W / 2 - delta && y < W / 2 + delta) return P;
        }
        
        while (d > 0)
        {
            int PX = P.x, PY = P.y;
            P.x += P.y == m && P.x < (H - m - 1) ? 1 : 
                P.y == (W - m - 1) && P.x > m ? -1 : 0;
            if(PX == P.x)
            P.y += P.x == m && P.y > m ? -1 : 
                P.x == (H - m - 1) && P.y < (W - m - 1) ? 1 : 0;
            --d;
        }
        
        return P;
    }
}