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

/**
 *
 * @author Leo
 */
class GameEnvironment extends Environment {
    private Character hero;
    
    @Override
    public void initializeEnvironment() {
        hero = new Character(new Point(100, 100), new Velocity(0, 0));
        this.getActors().add(hero);
        
        this.getActors().add(new Zombie(new Point(10, 10), new Velocity(0, 0)));
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


    
}
