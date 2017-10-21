import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Grid G = new Grid();
        for (int i = 0; i < height; i++)
        {
            List<Boolean> B = new ArrayList<>();
            for(char c: in.nextLine().toCharArray())
                B.add(c == '0');
            G.AddLine(B);
        }
        
        for(int R = 0; R < height; ++R)
            for(int C = 0; C < width; ++C)
            {
                if(!G.Nodes.get(R).get(C)) continue;
                System.out.println(G.GetNodeAndNeighbours(R, C));
            }
        
    }
}

class Grid
{
    public List<List<Boolean>> Nodes;
    
    public Grid()  { Nodes = new ArrayList<>(); }
    
    public void AddLine(List<Boolean> B) { Nodes.add(B); }
    
    public String GetNodeAndNeighbours(Integer Row, Integer Column)
    {
        String S = Column + " " + Row + " ";
        
        Integer C = Column + 1;
        for(; C < Nodes.get(Row).size() && !Nodes.get(Row).get(C); ++C);        
        S += C == Nodes.get(Row).size() ? "-1 -1 " : (C + " " + Row + " ");
        
        Integer R = Row + 1;
        for(; R < Nodes.size() && !Nodes.get(R).get(Column); ++R);        
        S += R == Nodes.size() ? "-1 -1" : (Column + " " + R);
        
        return S;
    }
}