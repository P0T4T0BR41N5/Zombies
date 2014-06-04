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

/**
 *
 * @author Leo
 */

public class Zombie extends Actor {
    
//<editor-fold defaultstate="collapsed" desc="Constructor / Initialization">
    private void initialize(){
        this.setImage(ResourceTools.loadImageFromResource("resources/zombie.png"));
    }
    
    public Zombie(Point position, Velocity velocity) {
        super(position, velocity);
        initialize();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static int MIN_HEALTH = 0;
    public static int MAX_HEALTH = 100;
    
    
    private boolean alive = true;
    private int health = 100;    
    
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
            if (Math.random() < 0.1) {
                AudioPlayer.play("/resources/zombie_growl_1.wav");
                System.out.println("sound 1");

            } else if (Math.random() < 0.2) {
                AudioPlayer.play("/resources/zombie_growl_2.wav");
                System.out.println("sound 2");

            } else if (Math.random() < 0.3) {
                AudioPlayer.play("/resources/zombie_growl_3.wav");
                System.out.println("sound 3");

            } else if (Math.random() < 0.4) {
                AudioPlayer.play("/resources/zombie_growl_4.wav");
                System.out.println("sound 4");

            } else if(Math.random() < 0.5) {
                AudioPlayer.play("/resources/zombie_growl_5.wav");
                System.out.println("sound 5");

            } else if (Math.random() < 0.6) {
                AudioPlayer.play("/resources/zombie_growl_6.wav");
                System.out.println("sound 6");

            } else if (Math.random() < 0.7) {
                AudioPlayer.play("/resources/zombie_growl_7.wav");
                System.out.println("sound 7");

            } else if (Math.random() < 0.8) {
                AudioPlayer.play("/resources/zombie_growl_8.wav");
                System.out.println("sound 8");

            } else if (Math.random() < 0.9) {
                AudioPlayer.play("/resources/zombie_growl_9.wav");
                System.out.println("sound 9");

            } else {
                AudioPlayer.play("/resources/zombie_growl_10.wav");
                System.out.println("sound 10");

            }
            this.stop();
            this.setPosition(-100, -100);
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
    
}