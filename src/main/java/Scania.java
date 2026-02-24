import java.awt.*;

public class Scania extends Vehicle implements BigCar {

    private double  loadAngle = 0;

    public Scania () {
        super(2, Color.white, 150, "Scania");
        this.stopEngine();
    }

    public double getLoadAngle() {
        return loadAngle;
    }

    public void setLoadAngle(double angle) {
        if (angle > 70 || angle < 0) {
            throw new IllegalArgumentException("Angle must be between 0 and 70!");
        }
        if (this.isDriving()) {
            throw new IllegalStateException("Cannot change angle while driving!");
        }
        this.loadAngle = angle;
    }

    @Override
    public void startEngine() {
        if (this.noDriving()) {
            throw new IllegalStateException("Cannot drive when ramp is up.");
        }
        super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if (this.noDriving()) {
            throw new IllegalStateException("Cannot drive when ramp is up.");
        }
        super.gas(amount);
    }

    public boolean isDriving() {
        return this.getCurrentSpeed() > 0;
    }

    public boolean noDriving() {
        return this.getLoadAngle() > 0;
    }
}
