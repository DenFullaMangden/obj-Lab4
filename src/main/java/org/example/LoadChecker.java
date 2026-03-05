package org.example;

public interface LoadChecker<T extends Storable> extends Positionable {
    void tryToLoad(Storable storable);

    Class<T> GetStorableType();
}
