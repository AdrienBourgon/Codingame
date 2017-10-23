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
        int N = in.nextInt();
        
        List<Node> Nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            
            Boolean B = false;
            for(Node No: Nodes)
                if(No.Char() == telephone.charAt(0))
                {
                    No.Process(telephone);
                    B = true;
                }
            if (!B){ Node No = new Node(); No.Process(telephone); Nodes.add(No); }
        }
        
        int C = 0;
        for(Node No: Nodes)
            C += No.CountNodes();
        
        System.out.println(C);
    }
}

class Node
{
    private char C;
    private List<Node> Nodes;
    
    public char Char() { return this.C; }
    
    public Node()
    {
        this.C = (char)0;
        this.Nodes = new ArrayList<>();
    }
    
    public void Process(String S)
    {
        this.C = S.charAt(0);
        
        if (S.length() > 1)
        {
            char C2 = S.charAt(1);
            
            Boolean Found = false;
            for(Node N: Nodes)
                if(N.Char() == C2)
                {
                    Found = true;
                    N.Process(S.substring(1));
                }
            if (!Found)
            {
                Node N = new Node();
                Nodes.add(N);
                N.Process(S.substring(1));
            }
        }
    }
    
    public int CountNodes()
    {
        int n = 1;
        
        for(Node N: Nodes)
            n += N.CountNodes();
        
        return n;
    }
}