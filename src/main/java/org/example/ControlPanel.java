package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final int X;

    public JPanel gasPanel = new JPanel();
    public JSpinner gasSpinner = new JSpinner();
    public int gasAmount = 10;
    public JLabel gasLabel = new JLabel("Amount of gas");

    public JButton gasButton = new JButton("Gas");
    public JButton brakeButton = new JButton("Brake");
    public JButton turboOnButton = new JButton("Turbo on");
    public JButton turboOffButton = new JButton("Turbo off");
    public JButton liftBedButton = new JButton("<html>Lift<br />bed</html>");
    public JButton lowerBedButton = new JButton("<html>Lower<br />bed</html>");
    public JButton startButton = new JButton("Start all cars");
    public JButton stopButton = new JButton("Stop all cars");
    public JButton addCarButton = new JButton("<html>Add<br />car</html>");
    public JButton removeCarButton = new JButton("<html>Remove<br />car</html>");

    public ControlPanel(int X) {
        this.X = X;
        this.initComponents();
    }

    private void initComponents() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
        this.gasSpinner = new JSpinner(spinnerModel);

        this.gasPanel.setLayout(new BorderLayout());
        this.gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        this.gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(gasPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4));
        buttonPanel.add(gasButton, 0);
        buttonPanel.add(turboOnButton, 1);
        buttonPanel.add(liftBedButton, 2);
        buttonPanel.add(addCarButton, 3);
        buttonPanel.add(brakeButton, 4);
        buttonPanel.add(turboOffButton, 5);
        buttonPanel.add(lowerBedButton, 6);
        buttonPanel.add(removeCarButton, 7);

        buttonPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        buttonPanel.setBackground(Color.CYAN);

        this.startButton.setBackground(Color.blue);
        this.startButton.setForeground(Color.green);
        this.startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));

        this.stopButton.setBackground(Color.red);
        this.stopButton.setForeground(Color.black);
        this.stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));

        this.add(buttonPanel);
        this.add(startButton);
        this.add(stopButton);
    }
}