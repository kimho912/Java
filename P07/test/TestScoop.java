package test;
import product.IceCreamFlavor;
import product.MixInFlavor;
import product.MixInAmount;
import product.Item;
import product.MixIn;
import product.Scoop;


public class TestScoop {
    private final static String errorFormat =
            "FAIL: expected \n  %s\nbut generate \n  %s\n\n";

    private static boolean failed(Item item, String expected) {
        String actual = item.name()+" "+item.description()+" "+item.cost()+" "+item.price();
        if (!actual.equals(expected)) {
            System.err.printf(errorFormat, expected, actual);
            return true;
        }
        return false;
    }
    private static boolean failed(Scoop scoop, String expected) {
        String actual = scoop.toString();
        if (!actual.equals(expected)) {
            System.err.printf(errorFormat, expected, actual);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int vector = 1;
        int result = 0;

        //TEST #1: Normal Case i) the name and description are typical English strings;
        //                    ii) the cost and price are positive integers;
        //                   iii) price > cost;
        MixInFlavor one = new MixInFlavor("Choco","Yummy",1,5);
        if(failed(one, "Choco Yummy 1 5")) result |= vector;
        
        //TEST #2: Empty description i) price = 0;
        //                          ii) cost == price;
        MixInFlavor two = new MixInFlavor("Strawberry","",0,0);
        if(failed(two,"Strawberry  0 0")) result |= vector;
        
        //TEST #3: Empty name and description
        MixInFlavor three = new MixInFlavor("","",-3,-5);
        if(failed(three, "  -3 -5")) result |= vector;
        
        //TEST #4: Foriegn language case for the name and description
        MixInFlavor four = new MixInFlavor("바닐라","맛있다",2,7);
        if(failed(four,"바닐라 맛있다 2 7")) result |= vector;

        vector <<= 1;
        
        IceCreamFlavor iceCreamFalvor = new IceCreamFlavor("Cheese", "Soft", 3, 5);
        if(failed(iceCreamFalvor, "Cheese Soft 3 5")) result |= vector;

        vector <<= 1;
        
        MixIn mixin1 = new MixIn(one, MixInAmount.Normal);
        MixIn mixin2 = new MixIn(four, MixInAmount.Drenched);
        
        //TEST #5: No MixIns
        Scoop scoop = new Scoop(iceCreamFalvor);
        if(failed(scoop, "Cheese")) result |= vector;
        
        //TEST #6: one MixIns
        scoop.addMixIn(mixin1);
        if(failed(scoop, "Cheese with Choco")) result |= vector;

        //TEST #7: two MixIns
        scoop.addMixIn(mixin2);
        if(failed(scoop, "Cheese with Choco, 바닐라 (Drenched)")) result |= vector;

        //Report result
        if(result != 0) System.err.println("\n### TEST FAILED###\n");
        System.exit(result);

    }
}
