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
        String ENCRYPT[] = in.nextLine().split(" ");
        
        Boolean Valid = true;
        
        if(ENCRYPT.length % 2 == 1)
            Valid = false;
        
        String Res = "";
        for(int i = 0; Valid && i < ENCRYPT.length; i += 2)
        {
            if(!ENCRYPT[i].equals("0") && !ENCRYPT[i].equals("00"))
            {
                Valid = false;
                continue;
            }
            Res += ENCRYPT[i].equals("0") ? ENCRYPT[i + 1].replace('0', '1') : ENCRYPT[i + 1];
        }
        
        if(Res.length() % 7 != 0)
            Valid = false;
        
        String CN = "";
        for(int i = 0; Valid && i < Res.length(); i += 7)
            CN += (char)Integer.parseInt(Res.substring(i, i + 7), 2);

        System.out.println(Valid ? CN : "INVALID");
    }
}