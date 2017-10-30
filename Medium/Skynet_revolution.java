import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        List<Node> Nodes = new ArrayList<>();
        
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        
        for(int i = 0; i < N; ++i) Nodes.add(new Node(i));
        
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            Nodes.get(N1).AddNode(Nodes.get(N2));
            Nodes.get(N2).AddNode(Nodes.get(N1));
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            Nodes.get(EI).IsGateway(true);
        }

        // game loop
        while (true)
        {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            
            List<Node> FastestWay = new ArrayList<Node>();
            
            for(Node Nod: Nodes)
                if(Nod.IsGateway())
                {
                    List<Node> Li = Nod.Way(SI, null);
                    if (FastestWay.size() == 0 || Li.size() < FastestWay.size())
                        FastestWay = Li;
                }

            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            if (FastestWay.size() > 1)
                System.out.println(FastestWay.get(0).getId() + " " + FastestWay.get(1).getId());
        }
    }
}

class Node
{
    protected int Id;
    protected List<Node> Links;
    protected Boolean Gateway = false;
    
    public int getId() { return this.Id; }
    public Boolean IsGateway() { return this.Gateway; }
    public void IsGateway(Boolean value) { this.Gateway = value; }
    
    public Node(int Id)
    { this.Id = Id; this.Links = new ArrayList<Node>(); }
    
    public void AddNode(Node N) { this.Links.add(N); }
    
    public List<Node> Way(int Id, List<Integer> Previous)
    {
        List<Node> N = new ArrayList<Node>();
        N.add(this);
        if (this.Id == Id) return N;
        
        if (Previous == null)
            Previous = new ArrayList<Integer>();
        
        Previous.add(this.Id);
        
        List<Node> L = new ArrayList<Node>();
        for (Node Nod: Links)
        {
            if (Previous.contains(Nod.Id)) continue;
            
            List<Node> Li = Nod.Way(Id, Previous);
            if (Li.size() > 0)
            if ((L.size() == 0 || Li.size() < L.size()))
                L = Li;
        }
        
        N.addAll(L);
        return N;
    }
}