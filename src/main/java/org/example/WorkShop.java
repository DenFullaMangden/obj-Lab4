package org.example;

import java.awt.geom.Point2D;

public class WorkShop<T extends SmallCar> implements LoadChecker<T>, Positionable {
    private final Class<T> carType;
    private final Storage<T> carBed;
    private final Point2D.Double position;

    public WorkShop(Class<T> carType, int capacity, Point2D.Double position) {
        this.carType = carType;
        this.position = position;
        this.carBed = new StorageUnit<T>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return WorkShop.this.getPosition();
            }
        };
    }

    public Point2D.Double getPosition() {
        return this.position;
    }

    public void load(T car) {
        try {
            this.carBed.load(car);
        }
        catch(Exception e) {
            System.out.println("Cannot load. Wrong type!");
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

    @Override
    public Class<T> GetStorableType() {
        return carType;
    }

    @Override
    public void tryToLoad(Storable storable) {
        if (!this.GetStorableType().isInstance(storable) || this.getPosition().distance(storable.getPosition()) > 20 ||
            storable.isStored()) {
            return;
        }
        else {
            this.load(this.GetStorableType().cast(storable));
        }
    }
}