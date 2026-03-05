package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.example.vehicle.RandomVehicleFactory;

public class CarModel implements CarModelInterface{

    private int delay;
    private int width;
    private int height;
    private Timer timer;
    public final List<Drivable> drivables = new ArrayList<Drivable>();
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
        for (Drivable drivable : drivables) {
            drivable.gas(gas);
        }
    }

    @Override
    public void brake(int amount) {
        double brake = ((double) amount/(this.delay*2));
        for (Drivable drivable : this.drivables) {
            drivable.brake(brake);
            }
    }

    @Override
    public void start() {
        for (Drivable drivable : this.drivables) {
            if (!drivable.getEngineOn()) {
                drivable.startEngine();
            }
        }
    }

    @Override
    public void stop() {
        for (Drivable drivable : this.drivables) {
            drivable.stopEngine();
        }
    }

    @Override
    public void setTurboOn() {
        for (Drivable drivable : this.drivables) {
            if (drivable instanceof Turbo) {
                ((Turbo) drivable).setTurboOn();
            }
        }
    }

    @Override
    public void setTurboOff() {
        for (Drivable drivable : this.drivables) {
            if (drivable instanceof Turbo) {
                ((Turbo) drivable).setTurboOff();
            }
        }
    }

    @Override
    public void liftBed() {
        for (Drivable drivable : this.drivables) {
            if (drivable instanceof Ramp) {
                ((Ramp) drivable).setRampUp();
            }
        }
    }

    @Override
    public void lowerBed() {
        for (Drivable drivable : this.drivables) {
            if (drivable instanceof Ramp) {
                ((Ramp) drivable).setRampDown();
            }
        }
    }

    @Override
    public void addVehicle() {
        if (this.drivables.size() < 10) {
            Drivable car = RandomVehicleFactory.createRandomVehicle(this.width-100, this.height-60);
            this.drivables.add(car);
            this.multicastStatusChange(new CarStatus(this.drivables));
        }
    }

    @Override
    public void removeVehicle() {
        if (!this.drivables.isEmpty()) {
            this.drivables.removeLast();
            this.multicastStatusChange(new CarStatus(this.drivables));
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
            for (Drivable drivable : CarModel.this.drivables) {

                int x = (int) Math.round(drivable.getPosition().getX());
                int y = (int) Math.round(drivable.getPosition().getY());
                if (x > (CarModel.this.width-100) || x < 0 || y > (CarModel.this.height-60) || y < 0) {
                    drivable.turnLeft();
                    drivable.turnLeft();
                }
                drivable.move();
            }

            for (CarLoader<?> loader : CarModel.this.carLoaders) {
                Class<?> type = loader.getCarType();
                for (Drivable drivable : drivables) {
                    if (type.isInstance(drivable)) {
                        loader.tryToLoad((SmallCar) drivable);
                    }
                }
            }

            multicastStatusChange(new CarStatus(CarModel.this.drivables));
        }
    }
}