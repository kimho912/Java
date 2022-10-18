package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Item {
    protected String name;
    protected String description;
    protected int cost;
    protected int price;

    public Item(String name, String description, int cost, int price) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }
    public Item(BufferedReader in) throws IOException {
        try(in) {
            name = in.readLine();
            description = in.readLine();
            cost = Integer.parseInt(in.readLine());
            price = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            System.err.println("Failed to read: " + e);
        }
    }
        
    public void save(BufferedWriter out) throws IOException {
        try(out) {
            out.write("" + name + '\n');        
            out.write("" + description + '\n');
            out.write("" + cost + '\n');      
            out.write("" + price + '\n');
            out.close();
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
        }
    } 
    public String name() {
        return name;
    }
    public String description() {
        return description;
    }
    public int price() {
        return price;
    }
    public int cost() {
        return cost;
    }
    @Override
    public String toString() {
        return name;
    }
}
