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
        List<Integer> Groups = new ArrayList<>();
        HashMap<Integer, Point> Rides = new HashMap<>(); //<StartingIndex, (Number of groups, Peoples)>
        
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int N = in.nextInt();
        for (int i = 0; i < N; i++)
            Groups.add(in.nextInt());
        
        long Dirhams = 0;
        int Index = 0, Peoples = 0, IndexDelta = 0;
        
        while(C > 0)
        {
            if(Rides.containsKey(Index)) //If we encountered this situation before
            {
                Dirhams += Rides.get(Index).y;
                Index = (int)((Index + Rides.get(Index).x) % N);
                --C;
                continue;
            }
            
            do //Looking how many groups can get on
            {
                Peoples += Groups.get((Index + IndexDelta) % N);
                ++IndexDelta;
            }while(Peoples <= L && IndexDelta <= N);
            
            --IndexDelta;
            Peoples -= Groups.get((Index + IndexDelta) % N);            
            Rides.put(Index, new Point(IndexDelta, Peoples)); //Adding it the the hashmap
            
            Index = (Index + IndexDelta) % N;                
            Dirhams += Peoples;
            Peoples = 0;
            IndexDelta = 0;
            --C;
        }

        System.out.println(Dirhams);
    }
}