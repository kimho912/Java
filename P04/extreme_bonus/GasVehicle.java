package vehicles;
/**
 * 
 * Handling a Gas-vehicle.
 * 
 * @author              Hyun Ho Kim
 * @version             1.0
 * @since               1.0
 * @license.agreement   Gnu General Public License 3.0
 * 
 */
public class GasVehicle implements Vehicle {
    /**
     * 
     * @see {@link Vehicles.Vehicle#Vehicle(int,String,String,BodyStyle)}
     * @param year              The model year 
     * @param make              The make or brand name of the manufacturer
     * @param model             The modle or brand name of the particular design
     * @param bodyStyle         The body style or general shape and features of the vehicle
     * @param milesPerGallon    the meiles per gallon of a car
     * @param gallonsInTank     the gallons in the tank
     * @since               1.0
     * 
     */
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle, double milesPerGallon, double gallonsInTank) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.bodyStyle = bodyStyle;
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }
    @Override
    /**
     * 
     * calculating the range for gas-vehicles
     * 
     * <p>see {@link vehicles.Vehicles#range()}
     * @since               1.0
     * 
     */
    public double range() {
        return gallonsInTank * milesPerGallon;
    }
    @Override
    /**
     * 
     * <p>see {@link vehicles.Vehicles#fuelConsumed(double)}
     * @return              the amount of gallons
     * @since               1.0
     * 
     */
    public double fuelConsumed(double miles) {
        if ((miles /milesPerGallon) > gallonsInTank)
            throw new ArithmeticException("More fuel is consumed than available in the tank!");
        else
        return miles / milesPerGallon; 
    }
    /**
     * 
     * <p>see {@link vehicles.Vehicles#dollarsToTravel(double)}
     * @since               1.0
     * @return the amount of cost
     */
    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }
    /**
     * dollars per one gallon
     * @since               1.0
     */
    public static double dollarsPerGallonOfGas;
    /**
     * miles per one gallon
     */
    private double milesPerGallon;
    /**
     * gallons in the tank
     */
    private double gallonsInTank;

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
