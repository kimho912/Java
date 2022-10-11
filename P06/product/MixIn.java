package product;

public class MixIn {
    private MixInFlavor flavor;
    private MixInAmount amount;

    public MixIn(MixInFlavor flavor, MixInAmount amount) {
        this.flavor = flavor;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return flavor.toString() + (!amount.equals(MixInAmount.Normal)
                                 ? " (" + amount + ")"
                                 : "");
    }
}
