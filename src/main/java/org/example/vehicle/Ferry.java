package org.example.vehicle;

import java.awt.*;
import java.awt.geom.Point2D;
import org.example.*;

public class Ferry extends Vehicle implements Ramp {

    private StorageUnit<SmallCar> carBed;
    private StorageUnit<BigCar> truckBed;
    private boolean rampUp;

    public Ferry(int capacity, Point2D.Double position) {
        super(20, Color.blue, 75, "org.vehicle.example.Ferry", position);
        this.setRampUp();

        this.carBed = new StorageUnit<SmallCar>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return Ferry.this.getPosition();
            }
        };
        this.truckBed = new StorageUnit<BigCar>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return Ferry.this.getPosition();
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
            throw new IllegalStateException("Cannot change ramp angle while example.vehicle is moving!");
        }
        this.rampUp = true;
    }

    @Override
    public void setRampDown() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while example.vehicle is moving!");
        }
        this.rampUp = false;
    }

    public void load(SmallCar car) {
        this.carBed.load(car);
    }

    public void load(BigCar car) {
        this.truckBed.load(car);
    }

    public Storable unload() {
        if (!truckBed.isEmpty())
            return this.truckBed.unload();
        return carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull() && truckBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty() && truckBed.isEmpty();
    }

}