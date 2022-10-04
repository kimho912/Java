public class TestScoop {
    //private static String errorFormat;

    private static boolean failed(Item item, String expected) {
        String actual = item.name()+" "+item.description()+" "+item.cost()+" "+item.price();
        if (!expected.equals(actual)) {
            System.err.println("\nERROR: The item is not same as expected.");
            System.err.println("  Expected: " + expected);
            System.err.println("  Actual:   " + actual);
            return true;
        }
        else
            return false;
    }
    private static boolean failed(Scoop scoop, String expected) {
        if (!expected.equals(scoop.toString())) {
            System.err.println("\nERROR: The item is not same as expected.");
            System.err.println("  Expected: " + expected);
            System.err.println("  Actual:   " + scoop.toString());
            return true;
        }
        else 
            return false;
    }

    public static void main(String[] args) {
        
        //TEST #1: Normal Case i) the name and description are typical English strings;
        //                    ii) the cost and price are positive integers;
        //                   iii) price > cost;
        MixInFlavor one = new MixInFlavor("Choco","Yummy",1,5);
        failed(one, "Choco Yummy 1 5");
        //TEST #2: Empty description i) price = 0;
        //                          ii) cost == price;
        MixInFlavor two = new MixInFlavor("Strawberry","",0,0);
        failed(two,"Strawberry  0 0");
        //TEST #3: Empty name and description
        MixInFlavor three = new MixInFlavor("","",-3,-5);
        failed(three, "  -3 -5");
        //TEST #4: Foriegn language case for the name and description
        MixInFlavor four = new MixInFlavor("바닐라","맛있다",2,7);
        failed(four,"바닐라 맛있다 2 7");
        
        IceCreamFlavor iceCreamFalvor = new IceCreamFlavor("Cheese", "Soft", 3, 5);
        failed(iceCreamFalvor, "Cheese Soft 3 5");
        //TEST #5: No MixIns
        Scoop scoopFirst = new Scoop(iceCreamFalvor);
        failed(scoopFirst, "Cheese");
        //TEST #6: one MixIns
        Scoop scoopSecond = new Scoop(iceCreamFalvor);
        failed(scoopSecond, "Cheese with Chocolate Chips (Extra)");

        //TEST #7: two MixIns
        Scoop scoopThird = new Scoop(iceCreamFalvor);
        failed(scoopThird, "Cheese with Crushed Snickers, Chocolate Chips (Extra)");

    }
}
