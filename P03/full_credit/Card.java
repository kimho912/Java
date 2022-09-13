public class Card {
    //Construtor
    private Rank rank;
    private Suit suit;

    //Methods
    public Card (Rank rank,Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return String.format("%s%s",rank, suit);
    }
}
