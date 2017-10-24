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
        
        Hashtable<Integer, Node> Nodes = new Hashtable<>();
        
        int n = in.nextInt(); // the number of relationships of influence
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();
            
            if(!Nodes.containsKey(x)) Nodes.put(x, new Node());
            if(!Nodes.containsKey(y)) Nodes.put(y, new Node());
            
            Nodes.get(x).Influence(Nodes.get(y));
        }

        int MaxCount = 0;
        
        for(Node No: Nodes.values())
            if(!No.IsInfluenced())
            {
                int Count = No.Count();
                
                if(Count > MaxCount) MaxCount = Count;
            }
    

        // The number of people involved in the longest succession of influences
        System.out.println(MaxCount);
    }
}
class Node
{
    private Boolean Influenced;
    public Boolean IsInfluenced() { return this.Influenced; }
    public void IsInfluenced(Boolean Influenced) { this.Influenced = Influenced; }
    
    private List<Node> Nodes;
    
    public Node()
    {
        this.Influenced = false;
        this.Nodes = new ArrayList<>();
    }
    
    public void Influence(Node N) { this.Nodes.add(N); N.IsInfluenced(true); }
    
    public int Count()
    {
        int i = 0;
        
        for(Node N: Nodes)
        {
            int j = N.Count();
            
            if(j > i) i = j;
        }
        return i + 1;
    }
}