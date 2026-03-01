package org.example.vehicle;

import org.example.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends Vehicle implements SmallCar {

    private final double trimFactor = 1.25;
    private boolean isStored;
    private Storage<?> currentStorage;

    public Volvo240(Point2D.Double position) {
        super(4, Color.black, 100, "Volvo 240", position);
        this.unStore();
    }

    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01 * this.trimFactor;
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