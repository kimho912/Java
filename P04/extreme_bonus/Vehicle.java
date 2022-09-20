package vehicles;
/**
 * 
 * Getting information about a vehicle.
 * 
 * @author              Hyun Ho Kim
 * @version             1.0
 * @since               1.0
 * @license.agreement   Gnu General Public License 3.0
 * 
 */
interface Vehicle {

    /**
     * 
     * @param year          The model year 
     * @param make          The make or brand name of the manufacturer
     * @param model         The modle or brand name of the particular design
     * @param bodyStyle     The body style or general shape and features of the vehicle
     * @since               1.0
     * 
     */
    /**
     * 
     * calculating how many miles it can go once
     * <p>see {@link vehicles.Gasvehicle#range()}
     * <p>see {@link vehicles.ElectricVehicle#range()}
     * @since               1.0
     * 
     */
    public abstract double range();
    /**
     * 
     * calculating how much fuel is consumed
     * <p>see {@link vehicles.Gasvehicle#fuelConsumed(double)}
     * <p>see {@link vehicles.ElectricVehicle#fuelConsumed(double)}
     * @param miles         taking miles that how many miles the user has driven
     * @return              the amount of gallons or energy
     * @since               1.0
     */
    public abstract double fuelConsumed(double miles);
    /**
     * 
     * calculating the cost for the trip
     * <p>see {@link vehicles.Gasvehicle#dollarsToTravel(double)}
     * <p>see {@link vehicles.ElectricVehicle#dollarsToTravel(double)}
     * @param miles taking miles that how many miles the user has driven
     * @return the amount of cost
     * @since               1.0
     */
    public abstract double dollarsToTravel(double miles);
    
}