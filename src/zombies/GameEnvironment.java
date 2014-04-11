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
import path.TrigonometryCalculator;

/**
 *
 * @author Leo
 */
class GameEnvironment extends Environment implements MouseMotionListener {
    private Character hero;
    private Crosshair crosshair;
    
    
    @Override
    public void initializeEnvironment() {
        hero = new Character(new Point(100, 100), new Velocity(0, 0));
        this.getActors().add(hero);
        
        crosshair = new Crosshair(new Point(100, 100), new Velocity(0, 0));
        this.getActors().add(crosshair);
        
        this.getActors().add(new Zombie(new Point(10, 10), new Velocity(0, 0)));
        addMouseMotionListener(this);
    }

    @Override
    public void timerTaskHandler() {

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setVelocity(new Velocity(-1, 0));
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setVelocity(new Velocity(1, 0));
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_A) ||
            (e.getKeyCode() == KeyEvent.VK_D)) {
         hero.stop();
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
        if (crosshair != null) {
         crosshair.setPosition(new Point (e.getPoint().x - 15, e.getPoint().y - 15));
         
         hero.setAngle((int) (TrigonometryCalculator.calculateAngle(hero.getCenterOfMass(), crosshair.getCenterOfMass()) * 360) );
         
         System.out.println("hero angle = " + TrigonometryCalculator.calculateAngle(hero.getCenterOfMass(), crosshair.getCenterOfMass()));
         System.out.println("hero angle = " + hero.getAngle());
         
         
        }
    }
//</editor-fold>


    
}
