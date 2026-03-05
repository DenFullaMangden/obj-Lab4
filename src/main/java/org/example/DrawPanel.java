package org.example;

import org.example.vehicle.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel implements CarObserver {

    private List<Drivable> drivables = new ArrayList<>();
    private List<LoadChecker<?>> loadCheckers = new ArrayList<>();

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage transportImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        try {
            this.volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg")));
            this.saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg")));
            this.scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg")));
            this.transportImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("/pics/CarTransport.jpg")));
            this.volvoWorkshopImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Drivable vehicle : this.drivables) {
            int x = (int) Math.round(vehicle.getPosition().getX());
            int y = (int) Math.round(vehicle.getPosition().getY());

            if (vehicle instanceof Volvo240) {
                g.drawImage(volvoImage, x, y, null);
            } else if (vehicle instanceof Saab95) {
                g.drawImage(saabImage, x, y, null);
            } else if (vehicle instanceof Scania) {
                g.drawImage(scaniaImage, x, y, null);
            } else if (vehicle instanceof CarTransport) {
                g.drawImage(transportImage, x, y, null);
            }
        }

        for (LoadChecker vehicle : this.loadCheckers) {
            int x = (int) Math.round(vehicle.getPosition().getX());
            int y = (int) Math.round(vehicle.getPosition().getY());

            if (vehicle instanceof WorkShop<?>) {
                g.drawImage(volvoWorkshopImage, x, y, null);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actOnStatusChange(CarStatus carStatus) {
        this.drivables = carStatus.getDrivables();
        this.loadCheckers = carStatus.getLoadCheckers();
        this.repaint();
    }
}