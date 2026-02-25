import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel implements CarObserver {

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    private ArrayList<Vehicle> vehicles = new java.util.ArrayList<>();
    public void syncCars(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

        for (Vehicle vehicle : vehicles) {
            int x = (int) Math.round(vehicle.getPosition().getX());
            int y = (int) Math.round(vehicle.getPosition().getY());

            if (vehicle instanceof Volvo240) {
                g.drawImage(volvoImage, x, y, null);
            } else if (vehicle instanceof Saab95) {
                g.drawImage(saabImage, x, y, null);
            } else if (vehicle instanceof Scania) {
                g.drawImage(scaniaImage, x, y, null);
            }
        }
    }

    @Override
    public void actOnStatusChange(CarStatus newStatus) {
        this.vehicles = newStatus.getVehicles();
        this.repaint();
    }
}
