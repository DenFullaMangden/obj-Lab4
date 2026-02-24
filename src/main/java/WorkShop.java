import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WorkShop<T extends Storable>  {

    private CarBed carBed;
    private Point2D.Double position;

    public WorkShop(int capacity, double x, double y) {
        this.carBed = new CarBed(capacity);
        this.position = new Point2D.Double(x,y);
    }

    public Point2D.Double getPosition() {
        return this.position;
    }

    public void load(T car) {
        try {
            this.carBed.load(car);
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }
    }

    public T unload() {
        return this.carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty();
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
            if (this.isFull()) {
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