import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        String[] R = { "", "TB;LB;RB", "LR;RL", "TB", "TL;RB", "TR;LB", "RL;LR", "TB;RB", "LB;RB", "TB;LB", "TL", "TR", "RB", "LB" };
        
        List<Room> Rooms = new ArrayList<>();
        for(String S: R) Rooms.add(new Room(S));
                
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        if (in.hasNextLine()) {
            in.nextLine();
        }
        
        int[][] Grid = new int[H][];
        
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            String[] Data = LINE.split(" ");
            int[] Line = new int[W];
            
            for(int j = 0; j < W; ++j) Line[j] = Integer.parseInt(Data[j]);            
            
            Grid[i] = Line;
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();

            Room.E_Sides Exit = Rooms.get(Grid[YI][XI]).Next(POS);


            YI += Exit == Room.E_Sides.Bottom ? 1 : 0;
            XI += Exit == Room.E_Sides.Right ? 1 : Exit == Room.E_Sides.Left ? -1 : 0;
            
            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println(XI + " " + YI);
        }
    }
}

class Room
{
    public enum E_Sides { Top, Left, Right, Bottom };
    public HashMap<E_Sides, E_Sides> Pathes;
    
    public Room(String D)
    {
        Pathes = new HashMap<>();
        
        String[] Data = D.split(";");
        
        for(String S: Data)
        {
            if(S.length() < 2) continue;
            char Start = S.charAt(0), End = S.charAt(1);
            
            Pathes.put(Start == 'T' ? E_Sides.Top : Start == 'L' ? E_Sides.Left : E_Sides.Right,
                    End == 'L' ? E_Sides.Left : End == 'R' ? E_Sides.Right : E_Sides.Bottom);
        }
    }
    
    public E_Sides Next(String E)
    {
        E_Sides Entry = E.equals("TOP") ? E_Sides.Top : E.equals("RIGHT") ? E_Sides.Right : E.equals("LEFT") ? E_Sides.Left : E_Sides.Top;
        
        if(Pathes.containsKey(Entry)) return Pathes.get(Entry);
        
        return Entry;
    }
}