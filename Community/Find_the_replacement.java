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
        String X = in.nextLine();
        String Y = in.nextLine();
        
        if(X.equals(Y)) System.out.println("NONE");
        else
        {
            Boolean Possible = true;
            HashMap<Character, Character> Replacement = new LinkedHashMap<>();
            
            first: for(int i = 0; i < X.length(); ++i)
            {
                char A = X.charAt(i), B = Y.charAt(i);
                
                if(A == B) continue;
                
                Replacement.put(A, B);
                
                for(int j = 0; j < X.length(); ++j)
                    if(X.charAt(j) == A && Y.charAt(j) != B)
                    {
                        Possible = false;
                        break first;
                    }                  
            }
                  
            if(!Possible)
                System.out.println("CAN'T");
            else
                for(char C: Replacement.keySet())
                    System.out.println(C + "->" + Replacement.get(C));
        }
    }
}