import java.util.ArrayList;

public class Scoop {
    private IceCreamFlavor flavor;
    private ArrayList<MixIn> mixins;

    public Scoop (IceCreamFlavor flavor) {
        this.flavor=flavor;
    }
    public void addMixIn (MixIn mixin) {
        mixins.add(mixin);
    }

    @Override
    public String toString () {
        if (mixins!=null) {
            StringBuilder str = new StringBuilder("");
            for (MixIn m : mixins) {
                str.append(m).append(",");
            }
            String result = str.toString();
            if (result.length()>0) {
                result = result.substring(0, result.length() - 1);
            }
        return flavor + " with " + result;
        }
        else
            return ""+flavor;
    }

    // public static void main(String[] args) {
    //     MixInFlavor mix = new MixInFlavor("Vanila","yummy",2,3);
    //     ArrayList<MixIn> m = new ArrayList<>();
    //     m.add(new MixIn(mix, MixInAmount.Extra));
    //     Scoop scoop = new Scoop(m);
    //     System.out.println(scoop.toString());
    // }
}
