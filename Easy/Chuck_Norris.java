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
        String MESSAGE = in.nextLine();

        String S = "";
        for(char c : MESSAGE.toCharArray())
            S += String.format("%07d", Integer.valueOf(Integer.toBinaryString((int)c)));
        
        S = S.replaceAll("01", "0 1");
        S = S.replaceAll("10", "1 0");
        S = S.replaceAll("(0+)", "00 $1");
        S = S.replaceAll("(1+)", "0 $1");
        S = S.replaceAll("1", "0");
        
        System.out.println(S);
    }
}