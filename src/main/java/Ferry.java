import java.awt.*;
import java.util.ArrayList;

public class Ferry extends Vehicle {

    private final int capacity;
    public boolean rampUp = true;
    private final ArrayList<Vehicle> loadedLandVehicles;

    public Ferry(int capacity) {
        super(2, Color.blue, 150, "Ferry");
        this.capacity = capacity;
        this.loadedLandVehicles = new ArrayList<>();
        this.stopEngine();
    }
//
//    public int getCapacity() {
//        return this.capacity;
//    }
//
//    public boolean isRampUp() {
//        return this.rampUp;
//    }
//
//    public void switchRamp() {
//        if (this.getCurrentSpeed() != 0) {
//            throw new IllegalStateException("Cannot operate ramp while moving.");
//        }
//        this.rampUp = !this.rampUp;
//    }
//
//    @Override
//    public void startEngine() {
//        if (!this.rampUp) {
//            throw new IllegalStateException("Cannot move with ramp down");
//        }
//        super.startEngine();
//    }
//
//    @Override
//    public void gas(double amount) {
//        if (!this.rampUp) {
//            throw new IllegalStateException("Cannot move with ramp down");
//        }
//        super.gas(amount);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return this.loadedLandVehicles.isEmpty();
//    }
//
//    @Override
//    public boolean isFull() {
//        return this.loadedLandVehicles.size() == this.capacity;
//    }
//
//    public void load(LandVehicle LandVehicle) {
//        if (this.rampUp) {
//            throw new IllegalStateException("Cannot load when ramp is up.");
//        }
//        if (isFull()) {
//            throw new IllegalStateException("The ferry is full.");
//        }
//        if (LandVehicle.isBeingTransported()) {
//            throw new IllegalStateException("LandVehicle is already being transported.");
//        }
//        loadedLandVehicles.add(LandVehicle);
//        LandVehicle.transportedBy(this);
//    }
//
//    @Override
//    public LandVehicle unload() {
//        if (this.rampUp) {
//            throw new IllegalStateException("Ramp must be down to unload LandVehicle.");
//        }
//        if (this.isEmpty()) {
//            throw new IllegalStateException("No LandVehicles to unload.");
//        }
//
//        LandVehicle LandVehicle = loadedLandVehicles.removeLast();
//        LandVehicle.untransport();
//        return LandVehicle;
//    }
//
//    @Override
//    public void moveLoaded() {
//        for (LandVehicle loadedLandVehicle : this.loadedLandVehicles) {
//            loadedLandVehicle.setPosition(this.getPosition());
//            if (loadedLandVehicle instanceof Transporter<?>) {
//                ((Transporter<?>) loadedLandVehicle).moveLoaded();
//            }
//        }
//    }
//
//    @Override
//    public void move() {
//        super.move();
//        this.moveLoaded();
//    }

}