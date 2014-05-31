/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class Character extends Actor {

    @Override
    public void move() {
        if (validateMove()) {
            super.move();
        } else {
            stop();
        }
    }

    public boolean validateMove() {
        if (moveValidator != null) {
            ArrayList<Point> corners = new ArrayList<>();

            Point topLeft = (Point) getPosition().clone();
            corners.add(topLeft);
            corners.add(new Point(topLeft.x + getObjectBoundary().width, topLeft.y));
            corners.add(new Point(topLeft.x + getObjectBoundary().width, topLeft.y + getObjectBoundary().height));
            corners.add(new Point(topLeft.x, topLeft.y + getObjectBoundary().height));

            for (Point point : corners) {
                point.x += getVelocity().x;
                point.y += getVelocity().y;
            }
            return moveValidator.validateMove(corners);
        }
        return true;
    }


//<editor-fold defaultstate="collapsed" desc="Constructor / Initialization">
    private void initialize() {
        this.setImage(ResourceTools.loadImageFromResource("resources/character.png"));
    }

    public Character(Point position, Velocity velocity, MoveValidatorIntf moveValidator) {
        super(position, velocity);
        this.moveValidator = moveValidator;
        initialize();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Painting">
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(this.getPosition().x, this.getPosition().y, 30, 5, true);
        graphics.setColor(Color.red);
        graphics.fillRect(this.getPosition().x, this.getPosition().y, 30 * health / MAX_HEALTH, 5);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    public static int MIN_HEALTH = 0;
    public static int MAX_HEALTH = 100;

    private int health = MAX_HEALTH;

    public static int MIN_SPEED = 0;
    public static int DEFAULT_SPEED = 3;
    public static int MAX_SPEED = 6;

    private int speed = DEFAULT_SPEED;
    
    private MoveValidatorIntf moveValidator;


    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void addToHealth(int health) {
        setHealth(getHealth() + health);
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        if (health < MIN_HEALTH) {
            this.health = MIN_HEALTH;
        } else if (health > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        } else {
            this.health = health;
        }
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the moveValidator
     */
    public MoveValidatorIntf getMoveValidator() {
        return moveValidator;
    }

    /**
     * @param moveValidator the moveValidator to set
     */
    public void setMoveValidator(MoveValidatorIntf moveValidator) {
        this.moveValidator = moveValidator;
    }
//</editor-fold>
}
