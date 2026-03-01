package org.example;

import org.example.vehicle.Saab95;
import org.example.vehicle.Scania;
import org.example.vehicle.Volvo240;

import java.awt.geom.Point2D;

public class Application {

    public static void main(String[] args) {
        CarModel carModel = new CarModel();
        CarView carView = new CarView("CarSim 7.0", 800, 800);
        CarController carController = new CarController(carModel);

        carModel.vehicles.add(new Volvo240(new Point2D.Double(0, 0)));
        carModel.vehicles.add(new Saab95(new Point2D.Double(0, 100)));
        carModel.vehicles.add(new Scania(new Point2D.Double(0, 200)));

        carModel.addObserver(carView.getDrawPanel());
        carView.getControlPanel().addCarControlListener(carController);

        carModel.startTimer(50, 560, 800);
    }
}