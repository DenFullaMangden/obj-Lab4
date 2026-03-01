package org.example.vehicle;

import java.awt.geom.Point2D;
import java.util.Random;

public class RandomVehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createRandomVehicle(double x, double y) {
        int choice = random.nextInt(3);
        Point2D.Double position = new Point2D.Double(x, y);

        return switch (choice) {
            case 0 -> new Volvo240(position);
            case 1 -> new Saab95(position);
            case 2 -> new Scania(position);
            default -> new Volvo240(position);
        };
    }
}