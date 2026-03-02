package org.example;

import java.awt.geom.Point2D;

public interface Storage<T extends Storable> extends Positionable {
    void load(T item);
    T unload();
    boolean isEmpty();
    boolean isFull();
}