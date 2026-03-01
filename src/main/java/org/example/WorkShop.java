package org.example;

import java.awt.geom.Point2D;

public class WorkShop<T extends Storable>  {

    private final StorageUnit<T> carBed;
    private final Point2D.Double position;

    public WorkShop(int capacity, Point2D.Double position) {
        this.carBed = new StorageUnit<T>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return WorkShop.this.getPosition();
            }
        };
        this.position = position;
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

}