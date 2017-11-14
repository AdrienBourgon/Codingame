import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        String[] Letters = new String[] {
            ".-","-...","-.-.","-..",".","..-.","--.","....",
            "..",".---","-.-",".-..","--","-.","---",".--.",
            "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
        };
        Scanner in = new Scanner(System.in);
        String L = in.next();
        int N = in.nextInt(), MinLength = Integer.MAX_VALUE, MaxLength = 0;
        HashMap<String, Integer> Dictionary = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < N; i++)
        {
            String S = toMorse(in.next(), Letters);
            if(S.length() < MinLength) MinLength = S.length();
            if(S.length() > MaxLength) MaxLength = S.length();
            
            if(!Dictionary.containsKey(S)) Dictionary.put(S, 0);
            Dictionary.put(S, Dictionary.get(S) + 1);
        }
        
        Memo.put(L, Message(L, Dictionary, MinLength, MaxLength));

        System.out.println(Memo.get(L));
    }
    
    private static HashMap<String, Long> Memo = new HashMap<>();
    private static long Message(String in, HashMap<String, Integer> Dictionary, int Min, int Max)
    {
        if(Memo.containsKey(in)) return Memo.get(in);
        
        if(in.length() == 0) return 1;
        else if(in.length() < Min) return 0;
        
        long N = 0;
        
        for(int i = Min, c = Max > in.length() ? in.length() : Max; i <= c; ++i)
            if(Dictionary.containsKey(in.substring(0, i)))
            {
                String S2 = in.substring(i);
                Memo.put(S2, Message(S2, Dictionary, Min, Max));
                N += Dictionary.get(in.substring(0, i)) * Memo.get(S2);
            }
        
        return N;
    }
    
    private static String toMorse(String Word, String[] Letters)
    {
        String r = "";
        for(char c: Word.toCharArray()) r+= Letters[c - 'A'];
        return r;
    }
}