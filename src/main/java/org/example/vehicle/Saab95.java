package org.example.vehicle;

import java.awt.*;
import java.awt.geom.Point2D;
import org.example.*;

public class Saab95 extends Vehicle implements SmallCar, Turbo {

    private boolean turboOn;
    private boolean isStored;
    private Storage<?> currentStorage;

    public Saab95(Point2D.Double position) {
        super(2, Color.red, 125, "Saab 95", position);
        this.turboOn = false;
        this.unStore();
    }
    
    public void setTurboOn() {
	    this.turboOn = true;
    }

    public void setTurboOff() {
	    this.turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (this.turboOn) turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
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