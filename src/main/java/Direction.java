import java.awt.geom.Point2D;

public enum Direction {
        NORTH("North"), EAST("East"), SOUTH("South"), WEST("West");
    
        private final String name;
        Direction(String name) {this.name = name;}
    
        @Override
        public String toString() {return name;}

        public Point2D.Double toPointVector() {
            return switch (this) {
                case NORTH -> new Point2D.Double(0.0, 1.0);
                case EAST -> new Point2D.Double(1.0, 0.0);
                case SOUTH -> new Point2D.Double(0.0, -1.0);
                case WEST -> new Point2D.Double(-1.0, 0.0);
            };
        }

        public Direction turnLeft() {
            return switch (this) {
                case NORTH -> WEST;
                case WEST -> SOUTH;
                case SOUTH -> EAST;
                case EAST -> NORTH;
            };
        }

        public Direction turnRight() {
            return switch (this) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }