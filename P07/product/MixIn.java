package product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class MixIn {
    private MixInFlavor flavor;
    private MixInAmount amount;

    public MixIn(MixInFlavor flavor, MixInAmount amount) {
        this.flavor = flavor;
        this.amount = amount;
    }
    public MixIn(BufferedReader in) throws IOException {
        try(in) {
            this.flavor = new MixInFlavor(in);
            this.amount = MixInAmount.valueOf(in.readLine());
        }
    }
    public void save(BufferedWriter out) throws IOException {
        try(out) {
            flavor.save(out);
            out.write(""+amount.toString()+'\n');
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
        }
    }
    @Override
    public String toString() {
        return flavor.toString() + (!amount.equals(MixInAmount.Normal)
                                 ? " (" + amount + ")"
                                 : "");
    }
}
