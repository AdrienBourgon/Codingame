import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Queue<Card> Deck1 = new LinkedList<>(), Deck2 = new LinkedList<>(), DeckTmp1 = new LinkedList<>(), DeckTmp2 = new LinkedList<>();
        int GameTurns = 0, Winner = 0;
        Boolean InWar = false;
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            Deck1.add(new Card(in.next())); // the n cards of player 1
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            Deck2.add(new Card(in.next())); // the m cards of player 2
        }
        
        while(!Deck1.isEmpty() && !Deck2.isEmpty())
        {
            if(!InWar) ++GameTurns;
            Winner = 0;
            Card C1 = Deck1.poll(), C2 = Deck2.poll();
            
            DeckTmp1.add(C1);
            DeckTmp2.add(C2);
            
            if(C1.Value() > C2.Value())
                Winner = 1;
            else if(C2.Value() > C1.Value())
                Winner = 2;
            else //War
            {
                InWar = true;
                if(Deck1.size() < 4 || Deck2.size() < 4)
                    break;
                
                for(int i = 0; i < 3; ++i)
                {
                    DeckTmp1.add(Deck1.poll());
                    DeckTmp2.add(Deck2.poll());
                }
                continue;
            }
            
            InWar = false;
            //Distribution des cartes au gagnant
            if (Winner == 1)
            {
                while(!DeckTmp1.isEmpty()) Deck1.add(DeckTmp1.poll());
                while(!DeckTmp2.isEmpty()) Deck1.add(DeckTmp2.poll());
            }
            else if (Winner == 2)
            {
                while(!DeckTmp1.isEmpty()) Deck2.add(DeckTmp1.poll());
                while(!DeckTmp2.isEmpty()) Deck2.add(DeckTmp2.poll());
            }
        }

        System.out.println(Winner == 0 ? "PAT" : (Winner + " " + GameTurns));
    }
}
class Card
{
    private int value;
    private char suit;
    
    private String[] Values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    
    public int Value() { return this.value; }
    
    public Card(String S)
    {
        String N = S.substring(0, S.length() - 1);
        for(int i = 0; i < Values.length; ++i)
            if(Values[i].equals(N))
            {
                this.value = i;
                break;
            }
        this.suit = S.charAt(S.length() - 1);            
    }
}