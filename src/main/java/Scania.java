import java.awt.*;

public class Scania extends Vehicle implements BigCar, Ramp {

    private double rampAngle;
    private boolean rampUp;
    private boolean isStored;
    private Storage<?> currentStorage;

    public Scania () {
        super(2, Color.white, 150, "Scania");
        this.unStore();
        this.setRampUp();
    }

    public double getRampAngle() {
        return rampAngle;
    }

    @Override
    public boolean getRampUp() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        return this.rampUp;
    }

    @Override
    public void setRampUp() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        this.setRampAngle(0);
        this.rampUp = true;
    }

    @Override
    public void setRampDown() {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        this.setRampAngle(70);
        this.rampUp = false;
    }

    public void setRampAngle(double angle) {
        if (this.getCurrentSpeed() > 0) {
            throw new IllegalStateException("Cannot change ramp angle while vehicle is moving!");
        }
        if (angle < 0 || angle > 70) {
            throw new IllegalArgumentException("Ramp angle must be between 0 and 70 degrees.");
        }
        this.rampAngle = angle;
        if (angle == 0) {
            this.rampUp = true;
        }
        else {
            this.rampUp = false;
        }
    }

    @Override
    public void startEngine() {
        if (!this.rampUp) {
            throw new IllegalStateException("Cannot start engine while ramp is down!");
        }
        super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if (!this.rampUp) {
            throw new IllegalStateException("Cannot accelerate while ramp is down!");
        }
        super.gas(amount);
    }

    @Override
    public boolean isStored() {
        return this.isStored;
    }

    @Override
    public void store(Storage<?> storage) {
        if (!isStored && storage != null) {
            this.isStored = true;
            this.currentStorage = storage;
            this.setPosition(currentStorage.getPosition());
            this.stopEngine();
        }
    }

    @Override
    public void unStore() {
        this.isStored = false;
        this.currentStorage = null;
    }

    @Override
    public void move() {
        if (isStored && currentStorage != null) {
            this.setPosition(currentStorage.getPosition());
        } else {
            super.move();
        }
    }
}
