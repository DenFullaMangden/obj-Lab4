package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.example.vehicle.Vehicle;
import org.example.vehicle.RandomVehicleFactory;

public class CarModel implements CarModelInterface{

    private int delay;
    private int width;
    private int height;
    private Timer timer;
    public final List<Vehicle> vehicles = new ArrayList<Vehicle>();
    public final List<CarLoader<?>> carLoaders = new ArrayList<CarLoader<?>>();
    private final List<CarObserver> observers = new ArrayList<CarObserver>();

    public void startTimer(int delay, int height, int width) {
        this.width = width;
        this.height = height;
        this.delay = delay;
        this.timer = new Timer(this.delay, new TimerListener());
        this.timer.start();
    }

    @Override
    public void gas(int amount) {
        double gas = ((double) amount/(this.delay*2));
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    @Override
    public void brake(int amount) {
        double brake = ((double) amount/(this.delay*2));
        for (Vehicle vehicle : this.vehicles) {
            vehicle.brake(brake);
            }
    }

    @Override
    public void start() {
        for (Vehicle vehicle : this.vehicles) {
            if (!vehicle.getEngineOn()) {
                vehicle.startEngine();
            }
        }
    }

    @Override
    public void stop() {
        for (Vehicle vehicle : this.vehicles) {
            vehicle.stopEngine();
        }
    }

    @Override
    public void setTurboOn() {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle instanceof Turbo) {
                ((Turbo) vehicle).setTurboOn();
            }
        }
    }

    @Override
    public void setTurboOff() {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle instanceof Turbo) {
                ((Turbo) vehicle).setTurboOff();
            }
        }
    }

    @Override
    public void liftBed() {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampUp();
            }
        }
    }

    @Override
    public void lowerBed() {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampDown();
            }
        }
    }

    @Override
    public void addVehicle() {
        if (this.vehicles.size() < 10) {
            Vehicle car = RandomVehicleFactory.createRandomVehicle(this.width-100, this.height-60);
            this.vehicles.add(car);
            this.multicastStatusChange(new CarStatus(this.vehicles));
        }
    }

    @Override
    public void removeVehicle() {
        if (!this.vehicles.isEmpty()) {
            this.vehicles.removeLast();
            this.multicastStatusChange(new CarStatus(this.vehicles));
        }
    }

    public void addObserver(CarObserver observer){
        observers.add(observer);
    }

    public void removeObserver(CarObserver observer){
        observers.remove(observer);
    }
    private void multicastStatusChange(CarStatus newStatus){
        for (CarObserver observer : observers){
            observer.actOnStatusChange(newStatus);
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : CarModel.this.vehicles) {

                int x = (int) Math.round(vehicle.getPosition().getX());
                int y = (int) Math.round(vehicle.getPosition().getY());
                if (x > (CarModel.this.width-100) || x < 0 || y > (CarModel.this.height-60) || y < 0) {
                    vehicle.turnLeft();
                    vehicle.turnLeft();
                }
                vehicle.move();
            }

            for (CarLoader<?> loader : CarModel.this.carLoaders) {
                Class<?> type = loader.getCarType();
                for (Vehicle vehicle : vehicles) {
                    if (type.isInstance(vehicle)) {
                        loader.tryToLoad((SmallCar) vehicle);
                    }
                }
            }

            multicastStatusChange(new CarStatus(CarModel.this.vehicles));
        }
    }
}