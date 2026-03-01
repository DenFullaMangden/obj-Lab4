package org.example;

import org.example.vehicle.Saab95;
import org.example.vehicle.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CarModel {

    private int delay;
    private Timer timer = new Timer(delay, new TimerListener());
    public final List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private final ArrayList<CarObserver> observers = new ArrayList<CarObserver>();

    public void startTimer(int delay) {
        this.timer = new Timer(delay, new TimerListener());
        this.timer.start();
    }

    void gas(int amount) {
        double gas = ((double) amount/100);
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount/100);
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    void start() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    void stop() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    void setTurboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Turbo) {
                ((Turbo) vehicle).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampUp();
            }
        }
    }

    void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Ramp) {
                ((Ramp) vehicle).setRampDown();
            }
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