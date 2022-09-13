import java.util.Stack;
import java.util.Collections;

public class Deck {
    private Stack<Card> deck;
    public class DeckEmpty extends Exception {
        public DeckEmpty() {
            //System.err.println(e.getMssage());
            System.exit(-1);
        }
        
    }

    public Deck() {
        deck = new Stack<Card>();
        for (Suit suit : Suit.values()) {
            for (int i=Rank.MIN; i<Rank.MAX+1; i++) {
                deck.push(new Card(rank,suit));
            }

        }
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
    public Card deal() {
        
        if(!deck.isEmpty()) {
            return deck.pop();
        }
        else
        {
            System.exit(-1);
            //throw new Exception(DeckEmpty);
        }
    }
    public boolean isEmpty() {return true;}


}

