import java.awt.*;

public class Volvo240 extends Vehicle implements SmallCar {

    private final double trimFactor = 1.25;
    private boolean isStored;
    private Storage<?> currentStorage;

    public Volvo240() {
        super(4, Color.black, 100, "Volvo 240");
    }

    @Override
    protected double speedFactor() {
        return this.getEnginePower() * 0.01 * this.trimFactor;
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