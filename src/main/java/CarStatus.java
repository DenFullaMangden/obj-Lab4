import java.util.ArrayList;

public class CarStatus {
    private final ArrayList<Vehicle> vehicles;

    public CarStatus(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}