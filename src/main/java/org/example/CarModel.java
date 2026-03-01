package org.example;

import org.example.vehicle.Saab95;
import org.example.vehicle.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CarModel implements CarModelInterface{

    private int delay;
    private Timer timer = new Timer(delay, new TimerListener());
    public final List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private final ArrayList<CarObserver> observers = new ArrayList<CarObserver>();

    public void startTimer(int delay) {
        this.timer = new Timer(delay, new TimerListener());
        this.timer.start();
    }

    @Override
    public void gas(int amount) {
        double gas = ((double) amount/100);
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    @Override
    public void brake(int amount) {
        double brake = ((double) amount/100);
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    @Override
    public void start() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    @Override
    public void stop() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    @Override
    public void setTurboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Turbo) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    @Override
    public void setTurboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Turbo) {
                ((Turbo) vehicle).setTurboOff();
            }
        }
    }

    @Override
    public void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampUp();
            }
        }
    }

    @Override
    public void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampDown();
            }
        }
    }

    @Override
    public void addVehicle() {

    }

    @Override
    public void removeVehicle() {

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
            for (Vehicle vehicle : vehicles) {
                vehicle.move();

                System.out.println(vehicle.getPosition());

                int x = (int) Math.round(vehicle.getPosition().getX());
                int y = (int) Math.round(vehicle.getPosition().getY());
                if (x > 700 || x < 0 || y > 500 || y < 0) {
                    vehicle.turnLeft();
                    vehicle.turnLeft();
                }
            }
            multicastStatusChange(new CarStatus(CarModel.this.vehicles));
        }
    }

}