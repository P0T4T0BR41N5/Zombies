/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import audio.AudioPlayer;
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
public class Zombie extends Actor {

    @Override
    public void move() {
        if ((alive) && (Math.random() > 0.995)) {
            grunt();
        }

        if (validateMove()) {
            super.move();
        } else {
            if (Math.random() >= .5) {
                if (getVelocity().x == 0) {
                    getVelocity().x = 2;
                }
                this.getVelocity().x *= -1;
            } else {
                if (getVelocity().y == 0) {
                    getVelocity().y = 2;
                }
                this.getVelocity().y *= -1;
            }
        }
    }

    public boolean validateMove() {
        if (getMoveValidator() != null) {
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
            return getMoveValidator().validateMove(corners);
        }
        return true;
    }

//<editor-fold defaultstate="collapsed" desc="Constructor / Initialization">
    private void initialize() {
        this.setImage(ResourceTools.loadImageFromResource("resources/zombie.png"));
    }

    public Zombie(Point position, Velocity velocity, MoveValidatorIntf moveValidator) {
        super(position, velocity);
        initialize();
        this.moveValidator = moveValidator;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    public static int MIN_HEALTH = 0;
    public static int MAX_HEALTH = 100;

    private boolean alive = true;
    private int health = 100;

    private MoveValidatorIntf moveValidator;

    /**
     * @return the Health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the Health to set
     */
    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
            setAlive(false);
        }
        this.health = health;
    }

    /**
     * @param health the Health to set
     */
    public void addToHealth(int health) {
        setHealth(this.health + health);
        if (health <= 0) {
        }
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        if ((this.alive) && !(alive)) {
            //play dying sound
            //maybe change image to dead guy on 
            grunt();
            this.stop();
            this.setPosition(-100000, -100000);
        }
        this.alive = alive;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Painting">
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        //draw a health bar
        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(this.getPosition().x, this.getPosition().y, 30, 5, true);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(this.getPosition().x, this.getPosition().y, 30 * health / MAX_HEALTH, 5);
    }
//</editor-fold>

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

    public void grunt() {
//        AudioPlayer.play("/resources/zombie_growl_1.wav");
        double number = Math.random();

        if (number < 0.1) {
            AudioPlayer.play("/resources/zombie_growl_1.wav");
        } else if (number < 0.3) {
            AudioPlayer.play("/resources/zombie_growl_3.wav");
        } else if (number < 0.4) {
            AudioPlayer.play("/resources/zombie_growl_4.wav");
        } else if (number < 0.5) {
            AudioPlayer.play("/resources/zombie_growl_5.wav");
        } else if (number < 0.6) {
            AudioPlayer.play("/resources/zombie_growl_6.wav");
        } else if (number < 0.7) {
            AudioPlayer.play("/resources/zombie_growl_7.wav");
        } else if (number < 0.8) {
            AudioPlayer.play("/resources/zombie_growl_8.wav");
        } else {
            AudioPlayer.play("/resources/zombie_growl_9.wav");
        }
    }

}
