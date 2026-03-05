package org.example;

import java.awt.geom.Point2D;

public interface Movable extends Positionable{

	void move();

	void turnLeft();

	void turnRight();

}