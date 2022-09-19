public class ElectricVehicle extends Vehicle {
    public ElectricVehicle(int year, String make, String model, BodyStyle bodyStyle, double whPerMile, double kwhInBattery) {
        super(year,make,model,bodyStyle);
        this.whPerMile = whPerMile;
        this.kwhInBattery = kwhInBattery;
    }
    @Override
    public double range() {
        return kwhInBattery / (whPerMile / 1000);
    }

    @Override
    public double fuelConsumed(double miles) {
        if ((miles * (whPerMile / 1000)) > kwhInBattery)
            throw new ArithmeticException("More energy is consumed than available in the batterey!");
        else    
        return miles * (whPerMile / 1000);
    }

    @Override
    public double dollarsToTravel(double miles) {
        return fuelConsumed(miles) * (centsPerKwhOfElectricity / 100);
    }

    public static double centsPerKwhOfElectricity;
    private double whPerMile;
    private double kwhInBattery;
}
