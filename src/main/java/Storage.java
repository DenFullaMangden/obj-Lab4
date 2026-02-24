import java.awt.geom.Point2D;

public interface Storage<T extends Storable> {
    void load(T item);
    T unload();
    boolean isEmpty();
    boolean isFull();
    Point2D.Double getPosition();
}