import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarCarrier extends Vehicle implements BigCar, Ramp {

    private CarBed carBed;
    private boolean isStored;
    private Storage<?> currentStorage;
    private boolean rampUp;

    public CarCarrier(int capacity) {
        super(2, Color.blue, 150, "Car Carrier");
        this.carBed = new CarBed(capacity);
        this.unStore();
        this.setRampUp();
    }

    @Override
    public boolean getRampUp() {
        return this.rampUp;
    }

    @Override
    public void setRampUp() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        this.rampUp = true;
    }

    @Override
    public void setRampDown() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        this.rampUp = false;
    }

    public void load(SmallCar car) {
        this.carBed.load(car);
    }

    public SmallCar unload() {
        return this.carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty();
    }

    @Override
    public boolean isStored() {
        return this.isStored;
    }

    @Override
    public void store(Storage<?> storage) {
        if (!isStored && storage != null) {
            this.isStored = true;
            this.currentStorage = storage;
            this.setPosition(currentStorage.getPosition());
            this.stopEngine();
        }
    }

    @Override
    public void unStore() {
        this.isStored = false;
        this.currentStorage = null;
    }

    @Override
    public void move() {
        if (isStored && currentStorage != null) {
            this.setPosition(currentStorage.getPosition());
        } else {
            super.move();
        }
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