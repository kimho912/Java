import java.util.ArrayList;
import java.util.Scanner;
/**
 * <p>see also {@link vehicles.Vehicle}
 * Calculating Vehicles' traveling cost.
 * 
 * @author              Hyun Ho Kim
 * @version             1.0
 * @since               1.0
 * 
 */
public class TravelCost {
    /**
     * printing out all the calculated examples
     * @since               1.0
     * @param args          argument
     */
    public static void main(String[] args) {
        ArrayList<? extends Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model S Plaid",   BodyStyle.Sedan,     297, 100  ));
        vehicles.add(new ElectricVehicle(2022, "Telsa",    "Model 3 LR",      BodyStyle.Sedan,     242,  82  ));
        vehicles.add(new ElectricVehicle(2022, "GM",       "Bolt",            BodyStyle.Hatchback, 286,  66  ));
        vehicles.add(new ElectricVehicle(2022, "Nissan",   "LEAF",            BodyStyle.Hatchback, 269,  60  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "Mustang Mach-E",  BodyStyle.SUV,       347,  88  ));
        vehicles.add(new ElectricVehicle(2022, "Ford",     "F-150 Lightning", BodyStyle.Truck,     511, 131  ));
        vehicles.add(new GasVehicle(     2022, "Ford",     "F-150",           BodyStyle.Truck,      25,  23  ));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "Prius Hybrid",    BodyStyle.Hatchback,  55,  11.4));
        vehicles.add(new GasVehicle(     2022, "Toyota",   "RAV4",            BodyStyle.Crossover,  31,  14.5));
        vehicles.add(new GasVehicle(     2022, "Nissan",   "Rogue",           BodyStyle.Hatchback,  33,  14.5));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica",        BodyStyle.Minivan,    24,  19  ));
        vehicles.add(new GasVehicle(     2022, "Chrysler", "Pacifica Hybrid", BodyStyle.Minivan,    30,  16.5));

        /**
         * scan a input
         */
        Scanner in = new Scanner(System.in);
        System.out.print("What is the price per gallon of gas (dollars)? ");
        /**
         * getting the price of a gallon from a user
         */
        GasVehicle.dollarsPerGallonOfGas = in.nextDouble();
        System.out.print("What is the price per kWh of electricity (cents)? ");
        /**
         * getting the price of one killowatt from a user   
         */
        ElectricVehicle.centsPerKwhOfElectricity = in.nextDouble();
        // ElectricVehicle.centsPerKwhOfElectricity = in.nextDouble();
        System.out.print("How many miles is your trip? ");
        /**
         * getting how many miles the user has driven
         */
        double mile = in.nextDouble();
        /**
         * print out the result
         */
        for (Vehicle v: vehicles) {
            System.out.printf("$ %6.2f (range %d) %s\n", v.dollarsToTravel(mile), Math.round(v.range()), v);
        }
    }
}