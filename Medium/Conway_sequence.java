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
        int R = in.nextInt();
        int L = in.nextInt();
        
        Line Li = new Line(R);
        
        for(int i = 1; i < L; ++i) 
            Li = new Line(Li);
        
        System.out.println(Li.ToString());
    }
}
class Line
{
    private List<Integer> Nums;
    public List<Integer> Numbers() { return Nums; }
    
    private Line() { Nums = new ArrayList<>(); }
    public Line(int N) { this(); Nums.add(N); }
    public Line(Line L) { this(); NumbersFromLine(L); }
    
    public String ToString()
    {
        String S = "";
        for(int i: Nums)
            S += (S.length() > 0 ? " " : "") + i;
        return S;
    }
    
    private void NumbersFromLine(Line L)
    {
        int n = -1, start = -1;
        
        for(int i = 0; i < L.Numbers().size(); ++i)
        {
            if (i == 0 || L.Nums.get(i) != n)
            {
                if(i > 0)
                {
                    Nums.add(i - start);
                    Nums.add(n);
                }
                start = i;
                n = L.Numbers().get(i);
            }
        }
        Nums.add(L.Numbers().size() - start);
        Nums.add(n);
    }
}