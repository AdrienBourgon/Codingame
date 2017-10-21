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
        Double LON = Double.valueOf(in.next().replace(',', '.'));
        Double LAT = Double.valueOf(in.next().replace(',', '.'));
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<Defibrilateur> Def = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Def.add(new Defibrilateur(in.nextLine()));
        }

        int Index = 0;
        for(int i = 0; i < Def.size(); ++i)
            if(Def.get(i).Distance(LON, LAT) < Def.get(Index).Distance(LON, LAT))
                Index = i;

        System.out.println(Def.get(Index).GetNom());
    }
}
class Defibrilateur
{
    private String Id, Nom, Adresse, Telephone;
    private Double Lon, Lat;
    
    public String GetNom() { return Nom; }
    
    public Defibrilateur(String Desc)
    {
        String[] Data = Desc.split(";");
        Id = Data[0];
        Nom = Data[1];
        Adresse = Data[2];
        Telephone = Data[3];
        
        Lon = Double.valueOf(Data[4].replace(',', '.'));
        Lat = Double.valueOf(Data[5].replace(',', '.'));
    }
    
    public Double Distance(Double Lon, Double Lat)
    {
        Double X = (Lon - this.Lon) * Math.cos((Lat + this.Lat) / 2);
        Double Y = Lat - this.Lat;
        
        return Math.sqrt(X*X + Y*Y) * 6371;
    }
}