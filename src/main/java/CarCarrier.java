import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarCarrier extends Vehicle {

    private CarBed carBed;

    public CarCarrier(int capacity) {
        super(2, Color.blue, 150, "Car Carrier");
        this.carBed = new CarBed(capacity);
    }

    public void loadCar(SmallCar car) {
        this.carBed.load(car);
    }

    public SmallCar unloadCar() {
        return this.carBed.unload();
    }

    private class CarBed implements Storage<SmallCar> {
        private final int capacity;
        private final ArrayList<SmallCar> loadedCars;

        public CarBed(int capacity) {
            this.capacity = capacity;
            this.loadedCars = new ArrayList<>();
        }

        public Point2D.Double getPosition() {
            return CarCarrier.this.getPosition();
        }

        public boolean isEmpty() { return this.loadedCars.isEmpty(); }

        public boolean isFull() { return this.loadedCars.size() == this.capacity; }

        @Override
        public void load(SmallCar car) {
            if (isFull()) {
                throw new IllegalStateException("The car carrier is full.");
            }
            if (car.isStored()) {
                throw new IllegalStateException("Car is already transported.");
            }
            loadedCars.add(car);
            car.store(this);
        }

        @Override
        public SmallCar unload() {
            if (isEmpty()) {
                throw new IllegalStateException("No cars to unload.");
            }

            SmallCar car = loadedCars.removeLast();
            car.unStore();
            return car;
        }

    }
}