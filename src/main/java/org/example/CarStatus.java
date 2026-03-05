package org.example;

import org.example.vehicle.Vehicle;
import java.util.List;

public class CarStatus {
    private final List<Drivable> vehicles;

    public CarStatus(List<Drivable> vehicles) {
        this.vehicles = List.copyOf(vehicles);
    }

    public List<Drivable> getVehicles() {
        return vehicles;
    }
}