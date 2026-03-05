package org.example;

public interface Drivable extends Movable {
    public double getCurrentSpeed();

    public boolean getEngineOn();

    public void startEngine();

    public void stopEngine();

    public void gas(double amount);

    public void brake(double amount);
}
