package org.example;

public class CarController implements CarControlListener {
    private CarModelInterface model;

    public CarController(CarModelInterface model) {
        this.model = model;
    }

    @Override
    public void gas(int amount) {
        model.gas(amount);
    }

    @Override
    public void brake(int amount) {
        model.brake(amount);
    }

    @Override
    public void start() {
        model.start();
    }

    @Override
    public void stop() {
        model.stop();
    }

    @Override
    public void setTurboOn() {
        model.setTurboOn();
    }

    @Override
    public void setTurboOff() {
        model.setTurboOff();
    }

    @Override
    public void liftBed() {
        model.liftBed();
    }

    @Override
    public void lowerBed() {
        model.lowerBed();
    }

    @Override
    public void addCar() {

    }

    @Override
    public void removeCar() {

    }
}