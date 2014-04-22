/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import environment.Environment;
import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author Leo
 */
class GameEnvironment extends Environment implements MouseMotionListener {

    private Character hero;
//    private Zombie zombie;
    private ArrayList<Zombie> zombies;
    private Crosshair crosshair;
    private int characterSpeed;
    private int zombieSpeed;

    @Override
    public void initializeEnvironment() {

        setCharacterSpeed(3);
        setZombieSpeed(2);

        setHero(new Character(new Point(100, 100), new Velocity(0, 0)));
        this.getActors().add(getHero());

        setCrosshair(new Crosshair(new Point(100, 100), new Velocity(0, 0)));
        this.getActors().add(getCrosshair());

        this.getActors().add(new Zombie(new Point(randomPoint()), new Velocity(0, 0)));
        addMouseMotionListener(this);

        zombies = new ArrayList<Zombie>();
        for (int i = 0; i < 5; i++) {
            Zombie myZombie = new Zombie(new Point(this.randomPoint()), new Velocity(0, 0));
            this.getActors().add(myZombie);
            this.getZombies().add(myZombie);
        }
    }

    private Point randomPoint() {
        return new Point((int) (Math.random() * 500), (int) (Math.random() * 500));

    }

    @Override
    public void timerTaskHandler() {
        for (Zombie aZombie : getZombies()){
            if (Math.random() > .9) {
                aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));                
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            getHero().setVelocity(new Velocity(-getCharacterSpeed(), 0));
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            getHero().setVelocity(new Velocity(getCharacterSpeed(), 0));
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            getHero().setVelocity(new Velocity(0, -getCharacterSpeed()));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            getHero().setVelocity(new Velocity(0, getCharacterSpeed()));
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_A)
                || (e.getKeyCode() == KeyEvent.VK_D)
                || (e.getKeyCode() == KeyEvent.VK_W)
                || (e.getKeyCode() == KeyEvent.VK_S)) {
            getHero().stop();

        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {

    }

//<editor-fold defaultstate="collapsed" desc="MouseMotionListener">
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (getCrosshair() != null) {
            getCrosshair().setPosition(new Point(e.getPoint().x - 15, e.getPoint().y - 15));

            getHero().setAngle((int) (TrigonometryCalculator.calculateAngle(getHero().getCenterOfMass(), getCrosshair().getCenterOfMass()) * 360));

            System.out.println("hero angle = " + TrigonometryCalculator.calculateAngle(getHero().getCenterOfMass(), getCrosshair().getCenterOfMass()));
            System.out.println("hero angle = " + getHero().getAngle());

        }
    }
//</editor-fold>

    /**
     * @return the hero
     */
    public Character getHero() {
        return hero;
    }

    /**
     * @param hero the hero to set
     */
    public void setHero(Character hero) {
        this.hero = hero;
    }

//    /**
//     * @return the zombie
//     */
//    public Zombie getZombie() {
//        return zombie;
//    }
//
//    /**
//     * @param zombie the zombie to set
//     */
//    public void setZombie(Zombie zombie) {
//        this.zombie = zombie;
//    }

    /**
     * @return the crosshair
     */
    public Crosshair getCrosshair() {
        return crosshair;
    }

    /**
     * @param crosshair the crosshair to set
     */
    public void setCrosshair(Crosshair crosshair) {
        this.crosshair = crosshair;
    }

    /**
     * @return the characterSpeed
     */
    public int getCharacterSpeed() {
        return characterSpeed;
    }

    /**
     * @param characterSpeed the characterSpeed to set
     */
    public void setCharacterSpeed(int characterSpeed) {
        this.characterSpeed = characterSpeed;
    }

    /**
     * @return the zombieSpeed
     */
    public int getZombieSpeed() {
        return zombieSpeed;
    }

    /**
     * @param zombieSpeed the zombieSpeed to set
     */
    public void setZombieSpeed(int zombieSpeed) {
        this.zombieSpeed = zombieSpeed;
    }

    /**
     * @return the zombies
     */
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    /**
     * @param zombies the zombies to set
     */
    public void setZombies(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
    }

}
