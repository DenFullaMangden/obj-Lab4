import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();
    WorkShop<Volvo240> workShop = new WorkShop<>(10);

    //methods:
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        Volvo240 volvo = new Volvo240();
        volvo.setPosition(new Point2D.Double(0,300));
        cc.cars.add(volvo);

        Saab95 saab = new Saab95();
        saab.setPosition(new Point2D.Double(0,100));
        cc.cars.add(saab);
        Scania scania = new Scania();
        scania.setPosition(new Point2D.Double(0,200));
        cc.cars.add(scania);
        cc.workShop.setPosition(new Point2D.Double(300,300));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        cc.frame.drawPanel.syncCars(cc.cars);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (x > 700 || x < 0 || y > 500 || y < 0) {
                    car.turnLeft();
                    car.turnLeft();
                }

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
//              System.out.println(car.getDirection());
//              System.out.println(car.getPosition());

//                if (car.getPosition().distance(new Point2D.Double(350, 350)) < 100 && car instanceof Volvo240
//                    && !workShop.isFull()) {
//                    if (!((Volvo240) car).isInWorkShop()) {
//                        workShop.load((Volvo240) car);
//                        ((Volvo240) car).load();
//                    }
//                }
            }
        }
    }


    // Calls the gas method for each car one
    void gas(int amount) {
        double gas = ((double) amount/100);
            for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount/100);
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void start() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stop() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    void setTurboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setLoadAngle(70);
            }
        }
    }

    void lowerBed() {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setLoadAngle(0);
            }
        }
    }

}
