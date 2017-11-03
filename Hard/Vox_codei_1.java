import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // width of the firewall grid
        int height = in.nextInt(); // height of the firewall grid
        if (in.hasNextLine())
            in.nextLine();
        
        Grid G = new Grid(width, height);
        
        for (int i = 0; i < height; i++)
            G.SetGridLine(i, in.nextLine().toCharArray()); // one line of the firewall grid

        // game loop
        Boolean FirstRound = true;
        while (true) {
            int rounds = in.nextInt(); // number of rounds left before the end of the game
            int bombs = in.nextInt(); // number of bombs left

            if(FirstRound)
            {
                FirstRound = false;
                G.Simulate(rounds, bombs);
            }

            System.out.println(G.NextMove());
        }
    }
}

class Grid
{
    private char[][] Map;
    private int Width, Height;
    private Queue<String> Moves;
    private Queue<char[][]> Grids;
    
    private Grid()
    {
        this.Width = 0;
        this.Height = 0;
        this.Moves = new LinkedList<>();
        this.Map = new char[1][];
    }
    public Grid(int Width, int Height)
    {
        this();
        this.Width = Width;
        this.Height = Height;
        this.Map = new char[Height][];
    }
    
    public void SetGridLine(int Position, char[] Line) { this.Map[Position] = Line; }
    public String NextMove() { if (Moves.isEmpty()) return "WAIT"; return Moves.poll(); }
    
    public void Simulate(int Rounds, int Bombs) { Simulate(Copy(), Rounds, Bombs); }
    
    private char[][] Copy()
    {
        char[][] C = new char[Height][];
        
        for(int i = 0; i < Height; ++i)
            C[i] = Arrays.copyOf(this.Map[i], Width);
        return C;
    }
    
    private void Simulate(char[][] DuplicateMap, int Rounds, int Bombs)
    {
        //Reset Moves queue
        Queue<String> NewMoves = new LinkedList<>();
        
        int TotalNodes = 0; //Number of nodes to destroy
        for(char[] L: DuplicateMap)
            for(char C: L)
                if(C == '@')
                    ++TotalNodes;
        
        int NewRounds = Rounds, NewBombs = Bombs;

        while(TotalNodes > 0)
        {
            //Replace countdown number
            for(int i = 0; i < Height; ++i)
                for(int j = 0; j < Width; ++j)
                {
                    if(DuplicateMap[i][j] > '0' && DuplicateMap[i][j] < '4')
                        DuplicateMap[i][j]--;
                    if (DuplicateMap[i][j] == '0')
                        DuplicateMap[i][j] = '.';
                }
            
            //Looking for the best place to set a bomb
            int[] Bomb = BestPlaceForBomb(DuplicateMap, NewBombs, TotalNodes);

            NewMoves.offer(Bomb[2] == 0 ? "WAIT" : Bomb[1] + " " + Bomb[0]);
            
            TotalNodes -= Bomb[2];
            --NewRounds;
            if(Bomb[0] != -1) --NewBombs;

            //Replace Bombs with points in the map
            DuplicateMap = DestroySurveillanceNodes(DuplicateMap, Bomb[0], Bomb[1]);
            
            //if there are still nodes to destroy
            if(TotalNodes > 0)
            {
                //If it's the end of the game
                if (NewRounds == 0)
                {
                    //Try to replace the first bomb with a timer and restart the simulation
                    Scanner Sc = new Scanner(NewMoves.element());
                    int Y = Sc.nextInt(), X = Sc.nextInt();
                    char[][] DuplicateMap2 = Copy();
                    DuplicateMap2[X][Y] = '3';
                    Simulate(DuplicateMap2, Rounds, Bombs);
                    return;
                }
            }
        }
        this.Moves = NewMoves;
    }   
    
    private int[] BestPlaceForBomb(char[][] DuplicateMap, int Bombs, int TotalNodes)
    {
        int MaxDestroyedNodes = 0, DestroyedNodes = 0, X = -1, Y = -1;
        
        for(int i = 0; i < Height; ++i)
        {
            for(int j = 0; j < Width; ++j)
            {
                if(DuplicateMap[i][j] != '.') continue;
                
                DestroyedNodes = NodesInRange(DuplicateMap, i, j);
                
                if(DestroyedNodes > MaxDestroyedNodes && DestroyedNodes >= TotalNodes / Bombs)
                {
                    MaxDestroyedNodes = DestroyedNodes;
                    X = i;
                    Y = j;
                }
            }
        }
        
        return new int[]{ X, Y, MaxDestroyedNodes };
    }
    private int NodesInRange(char[][] DuplicateMap, int X, int Y)
    {
        int NV = 0, NH = 0;
        
        //Vertically
        for(int i = X - 3; i < X + 4; ++i)
        {
            if (i < 0) continue;
            if (i == Height) break;
            if (i == X) continue;
            if (DuplicateMap[i][Y] == '#') { if(i < X) NV = 0; else break; }
            if (DuplicateMap[i][Y] == '@') NV++;
        }
        
        //Horizontally
        for(int i = Y - 3; i < Y + 4; ++i)
        {
            if (i < 0) continue;
            if (i == Width) break;
            if (i == Y) continue;
            if (DuplicateMap[X][i] == '#') { if(i < Y) NH = 0; else break; }
            if (DuplicateMap[X][i] == '@') NH++;
        }
        
        return NV + NH;
    }
    
    private char[][] DestroySurveillanceNodes(char[][] DuplicateMap, int X, int Y)
    {
        Boolean Dir1 = true, Dir2 = true;
        
        if(X == -1) return DuplicateMap;
        
        //Vertically
        for(int i = 1; i < 4; ++i)
        {
            //Top
            if(Dir1)
            {
                if(X - i < 0 || DuplicateMap[X - i][Y] == '#') Dir1 = false;
                else if (DuplicateMap[X - i][Y] == '@') DuplicateMap[X - i][Y] = '3';
            }
            
            //Bottom
            if(Dir2)
            {
                if (X + i >= Height || DuplicateMap[X + i][Y] == '#') Dir2 = false;
                else if (DuplicateMap[X + i][Y] == '@') DuplicateMap[X + i][Y] = '3';
            }
        }
        
        Dir1 = true; Dir2 = true;
        
        //Horizontally
        for(int i = 1; i < 4; ++i)
        {
            //Top
            if(Dir1)
            {
                if(Y - i < 0 || DuplicateMap[X][Y - i] == '#') Dir1 = false;
                else if (DuplicateMap[X][Y - i] == '@') DuplicateMap[X][Y - i] = '3';
            }
            
            //Bottom
            if(Dir2)
            {
                if (Y + i >= Width || DuplicateMap[X][Y + i] == '#') Dir2 = false;
                else if (DuplicateMap[X][Y + i] == '@') DuplicateMap[X][Y + i] = '3';
            }
        }
        
        return DuplicateMap;
    }
}