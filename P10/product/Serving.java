package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Serving {
    public Serving(Container container) {
        this.container = container;
        this.scoops = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }

    public Serving(BufferedReader in) throws IOException {
        this.container = new Container(in);
        this.scoops = new ArrayList<>();
        this.toppings = new ArrayList<>();

        int numScoops = Integer.parseInt(in.readLine());
        while(numScoops-- > 0) scoops.add(new Scoop(in));
        
        int numToppings = Integer.parseInt(in.readLine());
        while(numToppings-- > 0) toppings.add(new MixIn(in));
    }

    public void save(BufferedWriter out) throws IOException {
        container.save(out);

        out.write("" + scoops.size() + '\n');
        for(Scoop sc : scoops) sc.save(out);
            
        out.write("" + toppings.size() + '\n');
        for(MixIn top : toppings) top.save(out);
    }

    public void addScoop(Scoop scoop) {
        scoops.add(scoop);
    }

    public void addTopping(MixIn topping) {
        toppings.add(topping);
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder(container.toString());
        if(scoops.size() > 0) {
            String separator;
            if(scoops.size() == 1) {separator = " with a scoop of ";}
            else {separator = " with scoops of ";}
            for(Scoop s : scoops) {
                result.append(separator + s.toString());
                separator = ", ";
            }
        }
        if(toppings.size() > 0) {
            String separator = " and topped with ";
            for(MixIn t : toppings) {
                result.append(separator + t.toString());
                separator = ", ";
            }
        }
        return result.toString();
    }
    private Container container;
    private ArrayList<Scoop> scoops;
    private ArrayList<MixIn> toppings;
}
