package org.example;

public interface CarLoader<T extends SmallCar> {
    void tryToload(SmallCar car);

    Class<T> getCarType();
}
