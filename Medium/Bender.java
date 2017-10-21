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
        int L = in.nextInt();
        int C = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Case[][] Map = new Case[L][];
        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            Case[] Line = new Case[C];
            for(int j = 0; j < C; ++j)
                Line[j] = new Case(row.charAt(j));
            Map[i] = Line;
        }
        
        Bender b = new Bender(Map);
        b.StartMoving();        

        System.out.println(b.IsLooping() ? "LOOP" : b.GetMovements());
    }
}

class Bender
{
    private Case[][] Map;
    private Boolean IsInBreakerMode;
    private Direction[] Directions;
    private int iDir, dDir;
    private int xBender, yBender;
    
    private int MovementsIndexReset;
    private List<Movement> Movements;
    
    private Boolean Looping;
    public Boolean IsLooping() { return this.Looping; }
    
    public Bender(Case[][] Map)
    {
        this.Map = Map;
        
        IsInBreakerMode = false;
        
        Directions = new Direction[] {
            new Direction(Direction.E_Dir.SOUTH, 1, 0),
            new Direction(Direction.E_Dir.EAST, 0, 1),
            new Direction(Direction.E_Dir.NORTH, -1, 0),
            new Direction(Direction.E_Dir.WEST, 0, -1)
        };
        iDir = 0;
        dDir = 1;
        
        startingPoint: for(int i = 0; i < Map.length; ++i)
            for(int j = 0; j < Map[i].length; ++j)
                if(Map[i][j].IsStartingPoint)
                {
                    this.xBender = i;
                    this.yBender = j;
                    break startingPoint;
                }
                
        this.MovementsIndexReset = 0;
        this.Movements = new ArrayList<>();
        this.Looping = false;
    }
    
    public void StartMoving()
    {
        while(!Looping && !Move())
        {
            Movement M = Movements.get(this.Movements.size() - 1);
            for(int i = MovementsIndexReset; i < Movements.size() - 2; ++i)
                if(M.Equals(Movements.get(i)))
                {
                    Looping = true;
                    break;
                }
        }
    }
    public String GetMovements()
    {
        String r = "";
        for(Movement M: Movements)
            r += M.GetDirection().GetDir().name() + "\n";
        return r;
    }
    
    private Boolean CanMove()
    {
        Direction D = Directions[iDir];
        Case C = Map[xBender + D.GetdX()][yBender + D.GetdY()];
        
        return C.IsWalkable || C.IsBreakable && IsInBreakerMode;
    }
    public Boolean Move()
    {
        int nextIDir = dDir == 1 ? 0 : 3;
        while(!CanMove())
        {
            iDir = nextIDir;
            nextIDir += dDir;
            if(iDir < 0) iDir = Directions.length - 1;
            if(iDir == Directions.length) iDir = 0;
        }
        
        Direction D = Directions[iDir];
        xBender += D.GetdX();
        yBender += D.GetdY();
        Case C = Map[xBender][yBender];
        
        this.Movements.add(new Movement(D, xBender, yBender, IsInBreakerMode, dDir == -1));
                
        if(C.IsInverter) dDir *= -1;
        else if (C.IsBeer) IsInBreakerMode = !IsInBreakerMode;
        else if (C.IsBreakable && IsInBreakerMode){ C.Break(); MovementsIndexReset = this.Movements.size() - 1; }
        else if (C.IsTeleporter) Teleport();
        else if (C.IsSouth || C.IsEast || C.IsNorth || C.IsWest) ChangeDirection(C);
        else if (C.IsGoal) return true;
        
        return false;
    }
    
    private void Teleport()
    {
        for(int i = 0; i < Map.length; ++i)
            for(int j = 0; j < Map[i].length; ++j)
                if(Map[i][j].IsTeleporter && (i != xBender || j != yBender))
                {
                    xBender = i;
                    yBender = j;
                    return;
                }
    }
    private void ChangeDirection(Case C)
    {
        iDir = C.IsSouth ? 0 : 
            C.IsEast ? 1 : 
            C.IsNorth ? 2 : 3;
    }
}

class Movement
{
    private Direction Dir;
    private int X, Y;
    private Boolean BreakerMode, Inverted;
    
    public Direction GetDirection() { return this.Dir; }
    
    public Movement(Direction Dir, int X, int Y, Boolean BreakerMode, Boolean Inverted)
    {
        this.Dir = Dir;
        this.X = X;
        this.Y = Y;
        this.BreakerMode = BreakerMode;
        this.Inverted = Inverted;
    }
    
    public Boolean Equals(Movement M)
    {
        return M.Dir == this.Dir && M.X == this.X && M.Y == this.Y && M.BreakerMode == this.BreakerMode && M.Inverted == this.Inverted;
    }
}
class Direction
{
    public enum E_Dir {EAST, WEST, NORTH, SOUTH};
    private E_Dir Dir;
    private int dX, dY;
    
    public Direction(E_Dir Dir, int dX, int dY)
    {
        this.Dir = Dir;
        this.dX = dX;
        this.dY = dY;
    }
    
    public int GetdX() { return this.dX; }
    public int GetdY() { return this.dY; }
    public E_Dir GetDir() { return this.Dir; }
}

class Case
{
    public Boolean IsWalkable;
    public Boolean IsBreakable;
    public Boolean IsBeer;
    public Boolean IsTeleporter;
    public Boolean IsGoal;
    public Boolean IsInverter;
    public Boolean IsStartingPoint;
    public Boolean IsEast;
    public Boolean IsNorth;
    public Boolean IsWest;
    public Boolean IsSouth;
    
    public Case(char C)
    {
        IsBeer = C == 'B';
        IsInverter = C == 'I';
        IsGoal = C == '$';
        IsWalkable = C != '#' && C != 'X';
        IsBreakable = C == 'X';
        IsTeleporter = C == 'T';
        IsEast = C == 'E';
        IsWest = C == 'W';
        IsNorth = C == 'N';
        IsSouth = C == 'S';
        IsStartingPoint = C == '@';
    }
    
    public void Break()
    {
        IsBreakable = false;
        IsWalkable = true;
    }
}