import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ferry extends Vehicle implements Ramp{

    private CarBed carBed;
    private TruckBed truckBed;
    private boolean rampUp;

    public Ferry(int capacity) {
        super(20, Color.blue, 75, "Ferry");
        this.carBed = new CarBed(capacity);
        this.truckBed = new TruckBed(capacity);
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

    public void load(BigCar car) {
        this.truckBed.load(car);
    }

    public Storable unload() {
        if (!truckBed.isEmpty())
            return this.truckBed.unload();
        return carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull() && truckBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty() && truckBed.isEmpty();
    }

    private class CarBed implements Storage<SmallCar> {
        private final int capacity;
        private final ArrayList<SmallCar> loadedCars;

        public CarBed(int capacity) {
            this.capacity = capacity;
            this.loadedCars = new ArrayList<>();
        }

        public Point2D.Double getPosition() {
            return Ferry.this.getPosition();
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

    private class TruckBed implements Storage<BigCar> {
        private final int capacity;
        private final ArrayList<BigCar> loadedCars;

        public TruckBed(int capacity) {
            this.capacity = capacity;
            this.loadedCars = new ArrayList<>();
        }

        public Point2D.Double getPosition() {
            return Ferry.this.getPosition();
        }

        public boolean isEmpty() { return this.loadedCars.isEmpty(); }

        public boolean isFull() { return this.loadedCars.size() == this.capacity; }

        @Override
        public void load(BigCar car) {
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
        public BigCar unload() {
            if (isEmpty()) {
                throw new IllegalStateException("No cars to unload.");
            }
            BigCar car = this.loadedCars.removeLast();
            car.unStore();
            return car;
        }

    }
}