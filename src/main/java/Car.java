import java.awt.*;

public class Car extends LandVehicle {

    public Car(int nrDoors, Color color, int enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        this.stopEngine();
    }
}