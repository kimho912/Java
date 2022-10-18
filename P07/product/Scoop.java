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
        try(in) {
            this.flavor = new IceCreamFlavor(in);
            this.mixins = new ArrayList<>();
        }
    }
    public void save(BufferedWriter out) throws IOException {
        try(out) {
            flavor.save(out);
            if(mixins.size() > 0) {
                for(MixIn m : mixins) {
                    m.save(out);
                }
            }
            else {
                out.write(""+mixins.toString()+'\n');
            }
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
        }
    }
    public void addMixIn (MixIn mixin) {
        mixins.add(mixin);
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
