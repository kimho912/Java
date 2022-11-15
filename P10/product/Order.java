package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Order {
    public Order() { }
    public Order(BufferedReader in) throws IOException {
        this.servings = new ArrayList<>();
        int numServings = Integer.parseInt(in.readLine());
        while(numServings-- > 0) servings.add(new Serving(in));
    }

    public void save(BufferedWriter out) throws IOException {
        out.write("" + servings.size() + '\n');
        for(Serving sv : servings) sv.save(out);
    }

    public void addServing(Serving serving) {
        servings.add(serving);
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder(servings.toString());
        if(servings.size() > 0) {
            String separator = "Order1\n";
            for(Serving s : servings) {
                result.append(separator + s.toString());
                separator = "\n";
            }
        }
        return result.toString();
    }
    private ArrayList<Serving> servings;
}
