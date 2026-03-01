package org.example;

public interface CarControlListener {

    void gas(int amount);

    void brake(int amount);

    void start();

    void stop();

    void setTurboOn();

    void setTurboOff();

    void liftBed();

    void lowerBed();

    void addCar();

    void removeCar();
}