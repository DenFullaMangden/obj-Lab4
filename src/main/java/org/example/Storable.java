package org.example;

public interface Storable extends Positionable {
    boolean isStored();
    void unStore();
    void store(Storage<?> storage);
}