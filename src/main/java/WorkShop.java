import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WorkShop<T extends Storable> extends Vehicle {

    private CarBed carBed;

    public WorkShop(int capacity) {
        super(2, Color.blue, 150, "Car Carrier");
        this.carBed = new CarBed(capacity);
    }

    public void loadCar(T car) {
        this.carBed.load(car);
    }

    public T unloadCar() {
        return this.carBed.unload();
    }

    private class CarBed implements Storage<T> {
        private final int capacity;
        private final ArrayList<T> loadedCars;

        public CarBed(int capacity) {
            this.capacity = capacity;
            this.loadedCars = new ArrayList<>();
        }

        public Point2D.Double getPosition() {
            return WorkShop.this.getPosition();
        }

        public boolean isEmpty() { return this.loadedCars.isEmpty(); }

        public boolean isFull() { return this.loadedCars.size() == this.capacity; }

        @Override
        public void load(T car) {
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
        public T unload() {
            if (isEmpty()) {
                throw new IllegalStateException("No cars to unload.");
            }

            T car = loadedCars.removeLast();
            car.unStore();
            return car;
        }

    }
}