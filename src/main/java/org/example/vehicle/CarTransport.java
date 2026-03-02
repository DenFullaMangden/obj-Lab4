package org.example.vehicle;

import org.example.*;

import java.awt.*;
import java.awt.geom.Point2D;

public class CarTransport extends Vehicle implements BigCar, Ramp {

    private StorageUnit<SmallCar> carBed;
    private boolean isStored;
    private Storage<?> currentStorage;
    private boolean rampUp;

    public CarTransport(int capacity, Point2D.Double position) {
        super(2, Color.blue, 150, "Car Carrier", position);
        this.unStore();
        this.setRampUp();

        this.carBed = new StorageUnit<SmallCar>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return CarTransport.this.getPosition();
            }
        };
    }

    @Override
    public boolean getRampUp() {
        return this.rampUp;
    }

    @Override
    public void setRampUp() {
        if (this.getCurrentSpeed() > 0) {
            System.out.println("Cannot raise ramp while transport is moving!");
        }
        this.rampUp = true;
    }

    @Override
    public void setRampDown() {
        if (this.getCurrentSpeed() > 0) {
            System.out.println("Cannot lower ramp while transport is moving!");
        }
        this.rampUp = false;
    }

    @Override
    public void startEngine() {
        if (!this.getRampUp()) {
            System.out.println("Cannot start engine while ramp is down!");
            return;
        }
        super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if (!this.getRampUp()) {
            System.out.println("Cannot gas while ramp is down!");
            return;
        }
        super.gas(amount);
    }

    public void load(SmallCar car) {
        if (this.getRampUp()) {
            System.out.println("Cannot load while ramp is up!");
            return;
        }
        if (this.getCurrentSpeed() > 0) {
            System.out.println("Cannot load while moving!");
            return;
        }
        this.carBed.load(car);
    }

    public SmallCar unload() {
        if (this.getRampUp()) {
            System.out.println("Cannot unload while ramp is up!");
            return null;
        }
        if (this.getCurrentSpeed() > 0) {
            System.out.println("Cannot unload while moving!");
            return null;
        }
        return this.carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty();
    }

    @Override
    public boolean isStored() {
        return this.isStored;
    }

    @Override
    public void store(Storage<?> storage) {
        if (!isStored && storage != null) {
            this.isStored = true;
            this.currentStorage = storage;
            this.setPosition(currentStorage.getPosition());
            this.stopEngine();
        }
    }

    @Override
    public void unStore() {
        this.isStored = false;
        this.currentStorage = null;
    }

    @Override
    public void move() {
        if (isStored && currentStorage != null) {
            this.setPosition(currentStorage.getPosition());
        } else {
            super.move();
        }
    }

}