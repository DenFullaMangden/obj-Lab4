package org.example;

import org.example.vehicle.Vehicle;

import java.util.List;

public class CarStatus {
    private final List<Vehicle> vehicles;

    public CarStatus(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}