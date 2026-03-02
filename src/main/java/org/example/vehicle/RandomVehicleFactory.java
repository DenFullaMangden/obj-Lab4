package org.example.vehicle;

import java.awt.geom.Point2D;
import java.util.Random;

public class RandomVehicleFactory {
    private static final Random random = new Random();

    public static Vehicle createRandomVehicle(double maxWidth, double maxHeight) {
        double x = random.nextDouble(maxWidth);
        double y = random.nextDouble(maxHeight);
        Point2D.Double position = new Point2D.Double(x, y);
        int choice = random.nextInt(4);

        Vehicle vehicle = switch (choice) {
            case 1 -> new Saab95(position);
            case 2 -> new Scania(position);
            case 3 -> new CarTransport(10,position);
            default -> new Volvo240(position);
        };

        if (random.nextBoolean()) {
            vehicle.turnRight();
        }

        return vehicle;
    }
}