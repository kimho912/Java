package product;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Scoop {
    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins;

    public Scoop (IceCreamFlavor flavor) {
        this.flavor=flavor;
        this.mixins = new ArrayList<>();
    }
    public Scoop(BufferedReader in) throws IOException {
            this.flavor = new IceCreamFlavor(in);
            this.mixins = new ArrayList<>();
            int numMixIns = Integer.parseInt(in.readLine());
            while(numMixIns-- > 0) mixins.add(new MixIn(in));
    }
    public void save(BufferedWriter out) throws IOException {
            flavor.save(out);
            out.write("" + mixins.size() + '\n');
            for(MixIn mi : mixins) mi.save(out);
    }
    public void addMixIn (MixIn mixin) {
        mixins.add(mixin);
    }
    public int price() {
        int MixinSum = 0;
        for (MixIn m : mixins) {MixinSum += m.price();}
        return flavor.price + MixinSum;
    }
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder(flavor.toString());
        if(mixins.size() > 0) {
            String separator = " with ";
            for(MixIn m : mixins) {
                result.append(separator + m.toString());
                separator = ", ";
            }
        }
        return result.toString();
    }
}
