package emporium;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import product.Item;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.MixIn;
import product.Order;
import product.Container;

public class Emporium {
    public Emporium() { }
    public Emporium(BufferedReader in) throws IOException {
        int size = Integer.parseInt(in.readLine());
        while(size-- > 0) mixInFlavors.add(new MixInFlavor(in));

        size = Integer.parseInt(in.readLine());
        while(size-- > 0) iceCreamFlavors.add(new IceCreamFlavor(in));

        size = Integer.parseInt(in.readLine());
        while(size-- > 0) containers.add(new Container(in));

        size = Integer.parseInt(in.readLine());
        while(size-- > 0) orders.add(new Order(in));

    }
    public void save(BufferedWriter out) throws IOException {
        out.write("" + mixInFlavors.size() + '\n');
        for(MixInFlavor mif : mixInFlavors) mif.save(out);

        out.write("" + iceCreamFlavors.size() + '\n');
        for(IceCreamFlavor icf : iceCreamFlavors) icf.save(out);

        out.write("" + containers.size() + '\n');
        for(Container con : containers) con.save(out);

        out.write("" + orders.size() + '\n');
        for(Order od : orders) od.save(out);
    }
    
    public void addMixInFlavor(MixInFlavor flavor) {
        mixInFlavors.add(flavor);
    }
    public void addIceCreamFlavor(IceCreamFlavor flavor) {
        iceCreamFlavors.add(flavor);
    }
    public void addContainer(Container container) {
        containers.add(container);
    }
    public void addOrder(Order order) {
        orders.add(order);
    }

    public Object[] mixInFlavors() {
        return this.mixInFlavors.toArray();
    } 
    public Object[] iceCreamFlavors() {
        return this.iceCreamFlavors.toArray();
    }
    public Object[] containers() {
        return this.containers.toArray();
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    
    private ArrayList<MixInFlavor> mixInFlavors = new ArrayList<>();
    private ArrayList<IceCreamFlavor> iceCreamFlavors = new ArrayList<>();
    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
}
