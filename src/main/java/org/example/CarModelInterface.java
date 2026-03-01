package org.example;

public interface CarModelInterface {

    void gas(int amount);

    void brake(int amount);

    void start();

    void stop();

    void setTurboOn();

    void setTurboOff();

    void liftBed();

    void lowerBed();

    void addVehicle();

    void removeVehicle();

    void addObserver(CarObserver observer);
}