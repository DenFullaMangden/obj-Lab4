import java.util.ArrayList;

public class CarStatus {
    private ArrayList<Vehicle> vehicles;

    public CarStatus(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}