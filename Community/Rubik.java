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

        System.out.println((int)Math.pow(N, 3) - (N > 1 ? (int)Math.pow(N - 2, 3) : 0));
    }
}