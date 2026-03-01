package org.example.vehicle;

import java.awt.*;
import java.awt.geom.Point2D;
import org.example.Movable;
import org.example.Direction;

public abstract class Vehicle implements Movable {

    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private Point2D.Double position;
    private Direction direction;
    private boolean engineOn;

    public Vehicle(int nrDoors, Color color, double enginePower, String modelName, Point2D.Double position) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.position = position;
        this.direction = Direction.EAST;
        this.stopEngine();
    }

    public int getNrDoors() {
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public Color getColor() {
        return this.color;
    }

    public String getModelName() {
        return this.modelName;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Point2D.Double getPosition() {
        return this.position;
    }

    public boolean getEngineON() {
        return engineOn;
    }

    public void setColor(Color clr) {
	    color = clr;
    }

    public void startEngine() {
	    this.currentSpeed = 0.1;
        this.engineOn = true;
    }

    public void stopEngine() {
	    this.currentSpeed = 0;
        this.engineOn = false;
    }

    protected double speedFactor() {
        return this.enginePower * 0.01;
    }

    private void incrementSpeed(double amount) {
        this.currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount) {
        this.currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    protected void setPosition(Point2D.Double position) {
        this.position = new Point2D.Double(position.getX(), position.getY());
    }

    public void gas(double amount) {
        if (amount > 100 || amount < 0 || !this.engineOn) {
            System.out.println("Amount must be between 0 and 100 and the engine must be started.");
            return;
        }
        this.incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount > 100 || amount < 0) {
            System.out.println("Amount must be between 0 and 100");
            return;
        }
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        Point2D.Double directionVector = this.direction.toPointVector();
        double x = this.getPosition().getX();
        double y = this.getPosition().getY();
        this.position.setLocation(x + directionVector.getX() * this.currentSpeed,
                                  y + directionVector.getY() * this.currentSpeed);
    }

    @Override
    public void turnLeft() {
        this.direction  = this.direction.turnLeft(); 
    }

    @Override
    public void turnRight() {
        this.direction  = this.direction.turnRight();
    }

}