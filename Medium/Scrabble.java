import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        int[] Points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
        LinkedHashMap<String, Integer> Dictionary = new LinkedHashMap<>();
        HashMap<Character, Integer> Letters = new HashMap<>();
        
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String W = in.nextLine();
            Dictionary.put(W, WordPoints(W, Points));
        }
        String LETTERS = in.nextLine();
        for(char c: LETTERS.toCharArray())
        {
            if(!Letters.containsKey(c))
                Letters.put(c, 0);
            Letters.put(c, Letters.get(c) + 1);
        }
        
        String Word = "";
        
        for(String W: Dictionary.keySet())
            if(CanBeWord(W, Letters))
                if(Word.isEmpty() || !Word.isEmpty() && Dictionary.get(W) > Dictionary.get(Word))
                    Word = W;
        
        System.out.println(Word);
    }
    
    private static Boolean CanBeWord(String S, HashMap<Character, Integer> Letters)
    {
        for(char c: S.toCharArray())
        {
            int n = S.length() - S.replace(""+c, "").length();
            if(!Letters.containsKey(c) || n > Letters.get(c))
                return false;
        }        
        return true;
    }
    private static int WordPoints(String W, int[] P)
    {
        int Points = 0;
        
        for(char c: W.toCharArray()) Points += P[c - 'a'];
        
        return Points;
    }
}