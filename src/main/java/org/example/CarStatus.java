package org.example;

import java.util.List;

public class CarStatus {
    private final List<Drivable> drivables;
    private final List<LoadChecker<?>> loadCheckers;

    public CarStatus(List<Drivable> drivables, List<LoadChecker<?>> loadCheckers) {
        this.drivables = List.copyOf(drivables);
        this.loadCheckers = List.copyOf(loadCheckers);
    }

    public List<Drivable> getDrivables() {
        return drivables;
    }

    public List<LoadChecker<?>> getLoadCheckers() {
        return loadCheckers;
    }
}