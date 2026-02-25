import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarController {

    CarView frame;

    public static void main(String[] args) {
        CarModel carModel = new CarModel();

        Volvo240 volvo = new Volvo240();
        volvo.setPosition(new Point2D.Double(0,300));
        volvo.gas(100);
        carModel.vehicles.add(volvo);
        carModel.startTimer(50);

        while (true);
    }

}
