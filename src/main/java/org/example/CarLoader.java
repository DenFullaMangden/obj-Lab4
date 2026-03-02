package org.example;

public interface CarLoader<T extends SmallCar> {
    void tryToLoad(SmallCar car);

    Class<T> getCarType();
}
