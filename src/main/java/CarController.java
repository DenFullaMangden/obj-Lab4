import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarController {
    public static void main(String[] args) {
        CarModel carModel = new CarModel();
        CarView frame = new CarView("CarSim 1.0", carModel);

        Volvo240 volvo = new Volvo240();
        volvo.setPosition(new Point2D.Double(0, 300));
        carModel.vehicles.add(volvo);

        carModel.startTimer(50);
    }
}