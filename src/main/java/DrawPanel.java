import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; // Added this import
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel implements CarObserver {

    private List<Vehicle> vehicles = new ArrayList<>();
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    public void syncCars(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            this.volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            this.saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            this.scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            this.volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
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