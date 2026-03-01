package org.example;

public class CarController implements CarControlListener {
    private CarModelInterface model;

    public CarController(CarModelInterface model) {
        this.model = model;
    }

    @Override
    public void gas(int amount) {
        this.model.gas(amount);
    }

    @Override
    public void brake(int amount) {
        this.model.brake(amount);
    }

    @Override
    public void start() {
        this.model.start();
    }

    @Override
    public void stop() {
        this.model.stop();
    }

    @Override
    public void setTurboOn() {
        this.model.setTurboOn();
    }

    @Override
    public void setTurboOff() {
        this.model.setTurboOff();
    }

    @Override
    public void liftBed() {
        this.model.liftBed();
    }

    @Override
    public void lowerBed() {
        this.model.lowerBed();
    }

    @Override
    public void addCar() {
        this.model.addVehicle();
    }

    @Override
    public void removeCar() {
        this.model.removeVehicle();
    }
}