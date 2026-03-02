package org.example;

import java.awt.geom.Point2D;

public interface Movable {

	void move();

	void turnLeft();

	void turnRight();

	Point2D getPosition();
}