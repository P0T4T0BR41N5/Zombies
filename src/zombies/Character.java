/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import environment.Actor;
import environment.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import images.ResourceTools;
import java.awt.Point;

/**
 *
 * @author Leo
 */
public class Character extends Actor {

    private void initialize() {
        this.setImage(ResourceTools.loadImageFromResource("resources/character.png"));
    }

    public Character(Point position, Velocity velocity) {
        super(position, velocity);
        initialize();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(this.getPosition().x, this.getPosition().y, 30, 5, true);
        graphics.setColor(Color.red);
        graphics.fillRect(this.getPosition().x, this.getPosition().y, 30 * health / MAX_HEALTH, 5);
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    public static int MIN_HEALTH = 0;
    public static int MAX_HEALTH = 100;

    private int health = MAX_HEALTH;

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
//</editor-fold>
}
