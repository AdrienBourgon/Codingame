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
        int L = in.nextInt();
        int H = in.nextInt();
        
        MayaNumber.H = H;
        MayaNumber.L = L;
        
        List<String> Numbers = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            Numbers.add(in.next());
        }        
        int S1 = in.nextInt();
        MayaNumber MN1 = new MayaNumber(), MN2 = new MayaNumber();
        for (int i = 0; i < S1; i++) {
            MN1.Read(in.next());
        }
        int S2 = in.nextInt();
        for (int i = 0; i < S2; i++) {
            MN2.Read(in.next());
        }
        String operation = in.next();
        
        MayaNumber.Init(Numbers);
        
        MayaNumber M;
        switch (operation)
        {
            case "-":
                M = new MayaNumber(MN1.GetValue() - MN2.GetValue());
                break;
            case "*":
                M = new MayaNumber(MN1.GetValue() * MN2.GetValue());
                break;
            case "/":
                M = new MayaNumber(MN1.GetValue() / MN2.GetValue());
                break;
            default:
                M = new MayaNumber(MN1.GetValue() + MN2.GetValue());
        }

        String res = M.GetRepresentation(), resultat = "";
        Integer Start = 0;
        while(Start < res.length())
        {
            resultat += res.substring(Start, Start + L) + "\n";
            Start += L;
        }
        
        System.out.println(resultat);
    }
}

class MayaNumber
{
    private static List<MayaNumber> Numbers;
    public static Integer L, H;
    private Long Value;
    private String NumberRepresentation;
    
    public Long GetValue() { if (Value == -1) ValueFromRepresentation(); return this.Value; }
    public String GetRepresentation() { if(this.NumberRepresentation.isEmpty()) RepresentationFromValue(); return this.NumberRepresentation; }
    
    public MayaNumber(Long Value, String Representation)
    {
        this(Value);
        this.NumberRepresentation = Representation;
    }
    public MayaNumber()
    {
        this.Value = (long)-1;
        this.NumberRepresentation = "";
    }
    public MayaNumber(Long Value)
    {
        this();
        this.Value = Value;
    }
    
    public void Read(String S) { this.NumberRepresentation += S; }
    
    private void ValueFromRepresentation()
    {
        List<MayaNumber> MNs = new ArrayList<>();
        
        String S = this.NumberRepresentation;
        
        while(S.length() > 0)
        {
            String SS = S.substring(0, MayaNumber.L * H);
            
            for(MayaNumber MN: MayaNumber.Numbers)
            {
                if(SS.equals(MN.GetRepresentation()))
                {
                    MNs.add(MN);
                    S = S.substring(SS.length());
                    break;
                }
            }
        }
        
        this.Value = (long)0;
        for(int i = 0; i < MNs.size(); ++i)
            this.Value += (long)(MNs.get(i).GetValue() * Math.pow(20, MNs.size() - i - 1));
    }
    private void RepresentationFromValue()
    {
        Long L = this.Value;
        Integer Pow = 0;
        
        while(Math.pow(20, (Pow + 1)) <= L) ++Pow;
        
        while(Pow >= 0)
        {
            Double MPow = Math.pow(20, Pow);
            Integer I = (int)(L / MPow);
            L -= I* MPow.longValue();
            
            this.NumberRepresentation += MayaNumber.Numbers.get(I).GetRepresentation();
            --Pow;
        }
    }
    
    public static void Init(List<String> Numbers)
    {
        MayaNumber.Numbers = new ArrayList<>();
        
        for(int i = 0; i < 20; ++i)
        {
            MayaNumber MN = new MayaNumber((long)i);
            for(int j = 0; j < Numbers.size(); ++j)
                MN.Read(Numbers.get(j).substring(i*L, L*(i+1)));
            MayaNumber.Numbers.add(MN);
        }
    }
}