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
import static zombies.Character.MAX_HEALTH;

/**
 *
 * @author Leo
 */

public class Zombie extends Actor {
    //give a default picture...
    
    
    private boolean alive = true;
    private int health = 100;
    
    private void initialize(){
        this.setImage(ResourceTools.loadImageFromResource("resources/zombie.png"));
    }
    
     @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(this.getPosition().x, this.getPosition().y, 30, 5, true);
        graphics.setColor(Color.red);
        graphics.fillRect(this.getPosition().x, this.getPosition().y, 30 * health / MAX_HEALTH, 5);
    }
    public Zombie(Point position, Velocity velocity) {
        super(position, velocity);
        initialize();
    }

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
            //maybe change image to dead guy on ground
            this.stop();
        }
        this.alive = alive;
    }
    
}
