import java.util.*;
import java.io.*;
import java.math.*;
import java.awt.Point;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        JumpWindow JW = new JumpWindow(W, H);
        
        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            
            Point B = JW.JumpTo(bombDir, X0, Y0);

            // the location of the next window Batman should jump to.
            System.out.println(B.x + " " + B.y);
            
            X0 = B.x;
            Y0 = B.y;
        }
    }
}

class JumpWindow
{
    private int MinX, MaxX, MinY, MaxY;
    
    public JumpWindow (int MaxX, int MaxY)
    {
        this.MinX = 0;
        this.MaxX = MaxX;
        this.MinY = 0;
        this.MaxY = MaxY;
    }
    
    public Point JumpTo(String Dir, int X, int Y)
    {        
        Point XY = new Point(X, Y);
        
        char C1 = Dir.charAt(0), C2 = Dir.length() == 2 ? Dir.charAt(1) : 0;
        
        Boolean Up = C1 == 'U', Down = C1 == 'D', Right = C1 == 'R' || C2 == 'R', Left = C1 == 'L' || C2 == 'L';
        
        if (Up) MaxY = Y;
        else if (Down) MinY = Y;
        XY.y = Up || Down ? (MaxY + MinY)/2 : XY.y;
        
        if (Right) MinX = X;
        else if (Left) MaxX = X;
        XY.x = Right || Left ? (MaxX + MinX)/2 : XY.x;        
        
        return XY;
    }
}