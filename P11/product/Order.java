package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

import person.Customer;

public class Order {
    public Order(Customer customer) {
        this.servings = new ArrayList<>();
        this.customer = customer;
    }

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
    public Customer getCustomer() {return customer;}

    public Object[] servings() {
        return this.servings.toArray();
    }
    public int price() {
        int sumServing = 0;
        for (Serving s : servings) {sumServing += s.price();}
        return sumServing;
    }
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        String separator = "";
        if(servings.size() > 0) {
            for(Serving s : servings) {
                result.append(separator + s.toString());
                separator = "<br/>";
            }
        }
        return " $" + price() + " For " + customer + ":<br/>" + result.toString();
    }
    private ArrayList<Serving> servings;
    private Customer customer;
}
