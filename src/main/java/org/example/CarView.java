package org.example;

import javax.swing.*;
import java.awt.*;

public class CarView extends JFrame {
    private final DrawPanel drawPanel;
    private ControlPanel controlPanel;

    public CarView(String title, int X, int Y) {
        this.setTitle(title);
        this.controlPanel = new ControlPanel(X);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.drawPanel = new DrawPanel(X, Y - 240);
        this.controlPanel = new ControlPanel(X);

        this.add(drawPanel);
        this.add(controlPanel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}