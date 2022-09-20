package vehicles;
/**
 * 
 * Handling a electric-vehicle.
 * 
 * @author              Hyun Ho Kim
 * @version             1.0
 * @since               1.0
 * @license.agreement   Gnu General Public License 3.0
 * 
 */
public class ElectricVehicle implements Vehicle {
   /**
     * 
     * @see {@link Vehicles.Vehicle#Vehicle(int,String,String,BodyStyle)}
     * @param year              The model year 
     * @param make              The make or brand name of the manufacturer
     * @param model             The modle or brand name of the particular design
     * @param bodyStyle         The body style or general shape and features of the vehicle
     * @param whPerMile         the watt-hour per miles of a vehicle
     * @param kwhInBattery      the kilowatt-hour in the battery of a vehicle
     * @since                   1.0
     * 
     */
    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle, double whPerMile, double kwhInBattery) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;
    }
    @Override
    /**
     * 
     * calculating the range for electric-vehicles
     * 
     * see {@link vehicles.Vehicles#range()}
     * @since               1.0
     * 
     */
    public double range() {
        return kwhInBattery / (whPerMile / 1000);
    }
    /**
     * see {@link vehicles.Vehicles#fuelConsumed(double)}
     * @return              the amount of energy
     * @since               1.0
     */
    @Override
    public double fuelConsumed(double miles) {
        if ((miles * (whPerMile / 1000)) > kwhInBattery)
            throw new ArithmeticException("More energy is consumed than available in the batterey!");
        else    
        return miles * (whPerMile / 1000);
    }
    /**
     * 
     * see {@link vehicles.Vehicles#dollarsToTravel(double)}
     * @since               1.0
     * @return the amount of cost
     */
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * (centsPerKwhOfElectricity / 100);
    }
    /**
     * cents per one killowatt
     * @since               1.0
     */
    public static double centsPerKwhOfElectricity;
    /**
     * watts per mile
     */
    private double whPerMile;
    /**
     * kilowatts in the battery
     */
    private double kwhInBattery;

    @Override
    /**
     * 
     * print out the year,the make,the model, and the body style
     * 
     */
    public String toString() {
        return year + " " + make + " " + model + " " + bodyStyle; 
    }

    private int year;
    private String make;
    private String model;
    private BodyStyle bodyStyle;
}
