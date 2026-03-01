package org.example;

import javax.swing.*;

public class CarController {
    private CarModel model;
    private CarView view;

    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;
        initListeners();
    }

    private void initListeners() {
        ControlPanel cp = view.controlPanel;

        cp.gasSpinner.addChangeListener(e -> cp.gasAmount = (int) ((JSpinner) e.getSource()).getValue());
        cp.gasButton.addActionListener(e -> model.gas(cp.gasAmount));
        cp.brakeButton.addActionListener(e -> model.brake(cp.gasAmount));
        cp.startButton.addActionListener(e -> model.start());
        cp.stopButton.addActionListener(e -> model.stop());
        cp.turboOnButton.addActionListener(e -> model.setTurboOn());
        cp.turboOffButton.addActionListener(e -> model.setTurboOff());
        cp.liftBedButton.addActionListener(e -> model.liftBed());
        cp.lowerBedButton.addActionListener(e -> model.lowerBed());
    }

}