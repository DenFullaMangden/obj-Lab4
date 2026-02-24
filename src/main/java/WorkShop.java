import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WorkShop<T extends Storable>  {

    private StorageUnit<T> carBed;
    private Point2D.Double position;

    public WorkShop(int capacity, double x, double y) {
        this.carBed = new StorageUnit<T>(capacity) {
            @Override
            public Point2D.Double getPosition() {
                return WorkShop.this.getPosition();
            }
        };
        this.position = new Point2D.Double(x,y);
    }

    public Point2D.Double getPosition() {
        return this.position;
    }

    public void load(T car) {
        try {
            this.carBed.load(car);
        }
        catch(Exception e) {
            System.out.println("Cannot load. Wrong type!");
        }
    }

    public T unload() {
        return this.carBed.unload();
    }

    public boolean isFull() {
        return carBed.isFull();
    }

    public boolean isEmpty() {
        return carBed.isEmpty();
    }

}