public class Rank {
    
    //Fields constructor
    private int rank;
    public static final int MIN = 0;
    public static final int MAX = 9;

    //Methods
    public Rank(int rank) {
        if (rank < MIN || rank > MAX) {
            throw new Exception("Out of range");
        }
        else {
            this.rank = rank;
        }
        
    }
    public String toString() {
        return Integer.toString(rank);
    }

}