public class GasVehicle extends Vehicle {
    public GasVehicle(int year, String make, String model, BodyStyle bodyStyle, double milesPerGallon, double gallonsInTank) {
        super(year,make,model,bodyStyle);
        this.milesPerGallon = milesPerGallon;
        this.gallonsInTank = gallonsInTank;
    }
    @Override
    public double range() {
        return gallonsInTank * milesPerGallon;
    }
    @Override
    public double fuelConsumed(double miles) {
        if ((miles /milesPerGallon) > gallonsInTank)
            throw new ArithmeticException("More fuel is consumed than available in the tank!");
        else
        return miles / milesPerGallon; 
    }

    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * dollarsPerGallonOfGas;
    }

    public static double dollarsPerGallonOfGas;
    private double milesPerGallon;
    private double gallonsInTank;
}
