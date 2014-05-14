/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import environment.Environment;
import environment.Velocity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import path.TrigonometryCalculator;

/**
 *
 * @author Leo
 *
 * // TODO: // - ROTATE CHARACTER AND ZOMBIES // - ADD MAP // - * class
 * GameEnvironment extends Environment implements MouseMotionListener {
 *
 * private Character hero;
 */
// TODO:
// - ROTATE CHARACTER AND ZOMBIES
// - ADD MAP
// - 
class GameEnvironment extends Environment implements MouseMotionListener, ItemManagerResponseIntf {

    private Character hero;
//    private Zombie zombie;
    private ArrayList<Zombie> zombies;
    private Crosshair crosshair;
    private int characterSpeed;
    private int zombieSpeed;
    private GameState gameState = GameState.MAIN_MENU;

    @Override
    public void initializeEnvironment() {

    }

    @Override
    public void timerTaskHandler() {
        if (gameState == GameState.MAIN_MENU) {

        } else if (gameState == GameState.PAUSED) {

        } else if (gameState == GameState.RUNNING) {
            for (Zombie aZombie : getZombies()) {
                if (Math.random() >= .95) {
                    aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
                    aZombie.setAngle((int) (TrigonometryCalculator.calculateAngle(aZombie.getPosition(), hero.getPosition()) * 57));
                }
            }

        } else if (gameState == GameState.STARTING) {

            setCharacterSpeed(3);
            setZombieSpeed(2);

            setHero(new Character(new Point(100, 100), new Velocity(0, 0)));
            this.getActors().add(getHero());

            setCrosshair(new Crosshair(new Point(100, 100), new Velocity(0, 0)));
            this.getActors().add(getCrosshair());

//            this.getActors().add(new Zombie(new Point(randomPoint()), new Velocity(0, 0)));
            addMouseMotionListener(this);

            zombies = new ArrayList<>();
            for (int i = 0; i <= 0; i++) {
                Zombie myZombie = new Zombie(new Point(this.randomPoint()), new Velocity(0, 0));
                this.getActors().add(myZombie);
                this.getZombies().add(myZombie);
            }
            gameState = GameState.RUNNING;

        } else if (gameState == GameState.STORE_MENU) {

        } else if (gameState == GameState.RUNNING_TO_PAUSED) {
            for (Zombie zombie : zombies) {
                zombie.stop();
            }
            hero.stop();
            gameState = GameState.PAUSED;

        } else if (gameState == GameState.PAUSED_TO_RUNNING) {
            for (Zombie aZombie : getZombies()) {
                aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
            }
            gameState = gameState.RUNNING;

        } else if (gameState == GameState.RUNNING_TO_MENU) {
            for (Zombie zombie : zombies) {
                zombie.stop();
            }
            hero.stop();
            gameState = GameState.STORE_MENU;
        } else if (gameState == GameState.MENU_TO_RUNNING) {
            for (Zombie aZombie : getZombies()) {
                aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
            }
            gameState = gameState.RUNNING;
        }

        if ((hero != null) && (zombies != null)) {
            for (Zombie zombie : this.zombies) {
                if (this.hero.intersects(zombie)) {
                    if (Math.random() > .9) {
                        hero.addToHealth(-1);
                    }
                }
            }
        }

//        hero.setAngle((int) TrigonometryCalculator.calculateAngle(hero.getCenterOfMass(), crosshair.getCenterOfMass()));
//        intersect();
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (gameState == GameState.MAIN_MENU) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameState = GameState.STARTING;
            }
        } else if (gameState == GameState.PAUSED) {
            if (e.getKeyCode() == KeyEvent.VK_2) {
                gameState = GameState.PAUSED_TO_RUNNING;
            }

        } else if (gameState == GameState.RUNNING) {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                getHero().setVelocity(new Velocity(-getCharacterSpeed(), 0));
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                getHero().setVelocity(new Velocity(getCharacterSpeed(), 0));
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                getHero().setVelocity(new Velocity(0, -getCharacterSpeed()));
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                getHero().setVelocity(new Velocity(0, getCharacterSpeed()));
            } else if (e.getKeyCode() == KeyEvent.VK_1) {
                gameState = GameState.RUNNING_TO_MENU;
                showItemManager();
            } else if (e.getKeyCode() == KeyEvent.VK_2) {
                gameState = GameState.RUNNING_TO_PAUSED;
            }

        } else if (gameState == GameState.STARTING) {

        } else if (gameState == GameState.STORE_MENU) {

            if (e.getKeyCode() == KeyEvent.VK_1) {
                gameState = GameState.MENU_TO_RUNNING;
            }
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (gameState == GameState.MAIN_MENU) {

        } else if (gameState == GameState.PAUSED) {

        } else if (gameState == GameState.RUNNING) {
            if ((e.getKeyCode() == KeyEvent.VK_A)
                    || (e.getKeyCode() == KeyEvent.VK_D)
                    || (e.getKeyCode() == KeyEvent.VK_W)
                    || (e.getKeyCode() == KeyEvent.VK_S)) {
                getHero().stop();
            }
        } else if (gameState == GameState.STARTING) {

        } else if (gameState == GameState.STORE_MENU) {

        }

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (gameState == GameState.MAIN_MENU) {
            graphics.setColor(new Color(179, 51, 0, 200));
            graphics.fillRect(50, 50, 750, 450);

            graphics.setColor(Color.black);
            graphics.fillRect(100, 100, 650, 350);

            graphics.setColor(Color.red);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 100));
            graphics.drawString("Zombies", 260, 200);

            graphics.setColor(Color.GRAY);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 60));
            graphics.drawString("Press Space To Start", 190, 300);
        } else if (gameState == GameState.PAUSED) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(50, 50, 750, 450);

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 30));
            graphics.drawString("Paused", 390, 90);
            graphics.setColor(Color.GRAY);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 60));
            graphics.drawString("Press 2 To Continue", 200, 300);

        } else if (gameState == GameState.RUNNING) {

        } else if (gameState == GameState.STARTING) {

        } else if (gameState == GameState.STORE_MENU) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(50, 50, 750, 450);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(100, 100, 650, 350);

            graphics.setColor(Color.red);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 30));
            graphics.drawString("Store", 390, 90);

        }

    }

//<editor-fold defaultstate="collapsed" desc="MouseMotionListener">
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (getCrosshair() != null) {
            getCrosshair().setPosition(new Point(e.getPoint().x - 15, e.getPoint().y - 15));

            getHero().setAngle((int) (TrigonometryCalculator.calculateAngle(getHero().getCenterOfMass(), getCrosshair().getCenterOfMass()) * 57));

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

    private Point randomPoint() {
        return new Point((int) (Math.random() * 500), (int) (Math.random() * 500));

    }

    private void showItemManager() {
        JFrame frame = new JFrame("Item Manager");
        ItemList myItems = new ItemList();
        myItems.getItems().add(new Item("Item 1", "1"));
        myItems.getItems().add(new Item("Item 2", "2"));
        myItems.getItems().add(new Item("Item 3", "3"));
        myItems.getItems().add(new Item("Item 4", "4"));
        myItems.getItems().add(new Item("Item 5", "5"));
        myItems.getItems().add(new Item("Item 6", "6"));
        myItems.getItems().add(new Item("Item 7", "7"));
        myItems.getItems().add(new Item("Item 8", "8"));
        myItems.getItems().add(new Item("Item 9", "9"));
        myItems.getItems().add(new Item("Item 10", "10"));
        myItems.getItems().add(new Item("Item 11", "11"));
        myItems.getItems().add(new Item("Item 12", "12"));
        myItems.getItems().add(new Item("Item 13", "13"));
        myItems.getItems().add(new Item("Item 14", "14"));
        myItems.getItems().add(new Item("Item 15", "15"));

        ItemManager im = new ItemManager("Hello all the world!", myItems, (ItemManagerResponseIntf) this);
        frame.add(im);
        frame.setAlwaysOnTop(true);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(400, 600));
        frame.setVisible(true);
    }

    public void handleItemManagerResponse(ItemList itemList) {
        System.out.println("IM Response");
        for (Item item : itemList.getItems()) {
            System.out.println(item.getDisplay());
        }
    }
}
