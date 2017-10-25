import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        
        String CGX = "";
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            CGX += in.nextLine();
        }
        
        CGX = CGX.trim();
        String Res = "";
        int Tabs = 0, Index;
        Boolean InString = false, NodeFinished = false, EmptyBlock = false, NLRequired = false;
        for(Index = 0; Index < CGX.length(); ++Index)
        {
            char C = CGX.charAt(Index);
            
            if(!InString && (C == ' ' || C == '\t')) continue;
            
            if(NLRequired)
            {
                Res += "\n" + PrintTabs(C == ')' ? (Tabs - 1) : Tabs);
                NLRequired = false;
            }
            
            if (InString || C == '\'')
            {
                EmptyBlock = false;
                Res += C;
                if(C == '\'') InString = !InString;
                continue;
            }
            if (C == '(')
            {
                if(Index != 0 && !EmptyBlock) Res += "\n";
                Res += (!EmptyBlock ? PrintTabs(Tabs) : "") + C;
                NLRequired = true;
                EmptyBlock = true;
                ++Tabs;
                continue;
            }
            if (C == ')')
            {
                --Tabs;
                Res += EmptyBlock ? "" : "\n" + PrintTabs(Tabs);
                Res += C;
                EmptyBlock = false;
                continue;
            }
            EmptyBlock = false;
            if (C == ';')
            {
                Res += C;
                NLRequired = true;
                EmptyBlock = true;
                continue;
            }
            
            Res += C;
        }

        System.out.println(Res);
    }
    
    private static String PrintTabs(int Tabs)
    {
        String S = "";
        for(; Tabs > 0; --Tabs) S += "    ";
        return S;
    }
}