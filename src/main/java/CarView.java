import javax.swing.*;
import java.awt.*;

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    public DrawPanel drawPanel = new DrawPanel(X, Y - 240);
    public ControlPanel controlPanel = new ControlPanel(X);

    public CarView(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        this.add(controlPanel);

        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}