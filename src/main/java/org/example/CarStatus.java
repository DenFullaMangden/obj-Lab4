package org.example;

import org.example.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarStatus {
    private final List<Vehicle> vehicles;

    public CarStatus(List<Vehicle> vehicles) {
        this.vehicles = Collections.unmodifiableList(new ArrayList<>(vehicles));
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}