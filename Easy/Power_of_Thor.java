import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int TX = in.nextInt(); // Thor's starting X position
        int TY = in.nextInt(); // Thor's starting Y position

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            
            Boolean North, South, East, West;
            
            North = TY > lightY;
            South = TY < lightY;
            East = TX < lightX;
            West = TX > lightX;

            TX += East ? 1 : West ? -1 : 0;
            TY += North ? -1 : South ? 1 : 0;

            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println((North ? "N" : South ? "S" : "") + (East ? "E" : West ? "W" : ""));
        }
    }
}
