package org.example;

import org.example.vehicle.Saab95;
import org.example.vehicle.Scania;
import org.example.vehicle.Volvo240;

import java.awt.geom.Point2D;

public class Application {

    public static void main(String[] args) {
        CarModel carModel = new CarModel();
        CarView carView = new CarView("CarSim 7.0");
        carModel.addObserver(carView.drawPanel);

        Volvo240 volvo = new Volvo240(new Point2D.Double(0, 0));
        Saab95 saab = new Saab95(new Point2D.Double(100, 100));
        Scania scania = new Scania(new Point2D.Double(200, 200));

        carModel.vehicles.add(volvo);
        carModel.vehicles.add(saab);
        carModel.vehicles.add(scania);

        new CarController(carModel, carView);
        carModel.startTimer(50);
    }
}