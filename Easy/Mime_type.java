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
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        
        HashMap<String, String> Ext = new HashMap<String, String>();
        for (int i = 0; i < N; i++)
            Ext.put(in.next().toLowerCase(), in.next());
        
        in.nextLine();
        
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            
            String ext = FNAME.substring(FNAME.lastIndexOf(".") + 1).toLowerCase();
            
            System.out.println(
                FNAME.indexOf(".") == -1 ||  !Ext.containsKey(ext) 
                ? "UNKNOWN" 
                : Ext.get(ext));
            
        }
    }
}