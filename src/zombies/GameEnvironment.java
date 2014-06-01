/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import audio.AudioPlayer;
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
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import map.Map;
import map.MapVisualizerDefault;
import map.Obstacle;
import map.ObstacleEventHandlerIntf;
import map.ObstacleType;
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
class GameEnvironment extends Environment implements MouseMotionListener,
        ItemManagerResponseIntf, MoveValidatorIntf, ObstacleEventHandlerIntf {

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Character hero;
    private ArrayList<Zombie> zombies;
    private Crosshair crosshair;
    private int characterSpeed;
    private int zombieSpeed;

    private Map currentMap, zombieMap;
    private MapVisualizerDefault mapVisualizer;

    private GameState gameState;
    private int zombieHit = 0;
    private int zombieCount = 5;

    private boolean shotPause = false;

    Line2D shootLine;
    private long shootTime;
    private long shotDelay;

    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        System.out.println(gameState.toString());
        this.gameState = gameState;
        if (getGameState() == GameState.MAIN_MENU) {

        } else if (getGameState() == GameState.PAUSED) {

        } else if (getGameState() == GameState.RUNNING) {

        } else if (getGameState() == GameState.STARTING) {

            setCharacterSpeed(3);
            setZombieSpeed(2);

            setHero(new Character(new Point(200, 200), new Velocity(0, 2), this));
            this.getActors().add(getHero());

            setCrosshair(new Crosshair(new Point(100, 100), new Velocity(0, 0)));
            this.getActors().add(getCrosshair());

            addMouseMotionListener(this);

            zombies = new ArrayList<>();
            for (int i = 0; i < zombieCount; i++) {
                Zombie myZombie = new Zombie(new Point(this.randomPoint()), new Velocity(0, 0));
                this.getActors().add(myZombie);
                this.getZombies().add(myZombie);
            }

            for (Zombie aZombie : getZombies()) {
                aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
                aZombie.setAngle((int) (TrigonometryCalculator.calculateAngle(aZombie.getPosition(), hero.getPosition()) * 57));

            }

            System.out.println("starting to running");

            setGameState(GameState.RUNNING);

        } else if (getGameState() == GameState.STORE_MENU) {

        } else if (getGameState() == GameState.RUNNING_TO_PAUSED) {
            for (Zombie zombie : zombies) {
                zombie.stop();
            }
            hero.stop();
            setGameState(GameState.PAUSED);

        } else if (getGameState() == GameState.PAUSED_TO_RUNNING) {
            for (Zombie aZombie : getZombies()) {
                if (aZombie.isAlive()) {
                    aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
                }
            }
            setGameState(getGameState().RUNNING);

        } else if (getGameState() == GameState.RUNNING_TO_MENU) {
            for (Zombie zombie : zombies) {
                zombie.stop();
            }
            hero.stop();
            setGameState(GameState.STORE_MENU);
        } else if (getGameState() == GameState.MENU_TO_RUNNING) {
            for (Zombie aZombie : getZombies()) {
                if (aZombie.isAlive()) {
                    aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
                }
            }
            setGameState(GameState.RUNNING);
        } else if (getGameState() == GameState.DEAD) {
            for (Zombie zombie : zombies) {
                zombie.stop();
            }
            hero.stop();
            this.crosshair.setImage(null);
        }

    }

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
//</editor-fold>

    private Point randomPoint() {
        return new Point((int) (Math.random() * 500), (int) (Math.random() * 500));

    }

    @Override
    public void initializeEnvironment() {
        mapVisualizer = new MapVisualizerDefault(true, false);

        zombieMap = MapBin.getZombieMap();
        configureMap(zombieMap);
        currentMap = zombieMap;

        setGameState(GameState.MAIN_MENU);

        if (mapVisualizer != null) {
            mapVisualizer.toggleShowAllObjects();
        }
    }

    private void configureMap(Map map) {
        map.setMapVisualizer(mapVisualizer);
        map.setObstacleEventHandler(this);
    }

    @Override
    public void timerTaskHandler() {
        if (gameState == GameState.MAIN_MENU) {
        } else if (gameState == GameState.PAUSED) {
        } else if (gameState == GameState.RUNNING) {
            if (System.currentTimeMillis() - shootTime > 40) {
                shootLine = null;
            }

            if (System.currentTimeMillis() - shotDelay > 200) {
                shotPause = false;
            }

            for (Zombie aZombie : getZombies()) {
                if ((Math.random() >= .98) && aZombie.isAlive()) {
                    aZombie.setVelocity(TrigonometryCalculator.calculateVelocity(aZombie.getPosition(), hero.getPosition(), 2));
                    aZombie.setAngle((int) (TrigonometryCalculator.calculateAngle(aZombie.getPosition(), hero.getPosition()) * 57));
                }
            }

            if ((hero != null) && (zombies != null)) {
                for (Zombie zombie : this.zombies) {
                    if (this.hero.intersects(zombie)) {
                        if ((Math.random() > .9) && (zombie.isAlive())) {
                            hero.addToHealth(-2);
                        }
                    }
                }
            }

            if (hero != null) {
                if (Math.random() > .99) {
                    hero.addToHealth(2);
                }
                if (hero.getHealth() <= 0) {
                    setGameState(gameState.DEAD);
                }
            }
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (getGameState() == GameState.MAIN_MENU) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                setGameState(GameState.STARTING);
            }
        } else if (getGameState() == GameState.PAUSED) {
            if (e.getKeyCode() == KeyEvent.VK_2) {
                setGameState(GameState.PAUSED_TO_RUNNING);
            }
        } else if (getGameState() == GameState.RUNNING) {

            if (e.getKeyCode() == KeyEvent.VK_A) {
                getHero().setVelocity(new Velocity(-getCharacterSpeed(), 0));
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                getHero().setVelocity(new Velocity(getCharacterSpeed(), 0));
            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                getHero().setVelocity(new Velocity(0, -getCharacterSpeed()));
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                getHero().setVelocity(new Velocity(0, getCharacterSpeed()));
            } else if (e.getKeyCode() == KeyEvent.VK_1) {
                setGameState(GameState.RUNNING_TO_MENU);
                showItemManager();
            } else if (e.getKeyCode() == KeyEvent.VK_2) {

                setGameState(GameState.RUNNING_TO_PAUSED);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                Point newPosition = (Point) currentMap.getPosition().clone();
                newPosition.y -= 10;
                currentMap.setPosition(newPosition);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Point newPosition = (Point) currentMap.getPosition().clone();
                newPosition.y += 10;
                currentMap.setPosition(newPosition);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Point newPosition = (Point) currentMap.getPosition().clone();
                newPosition.x -= 10;
                currentMap.setPosition(newPosition);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Point newPosition = (Point) currentMap.getPosition().clone();
                newPosition.x += 10;
                currentMap.setPosition(newPosition);
            } else if (e.getKeyCode() == KeyEvent.VK_E) {
                if (mapVisualizer != null) {
                    mapVisualizer.toggleShowAllObjects();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                shoot(this.crosshair.getCenterOfMass());
            }

        } else if (gameState == GameState.STARTING) {
        } else if (gameState == GameState.STORE_MENU) {
            setGameState(GameState.RUNNING_TO_PAUSED);
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (getGameState() == GameState.MAIN_MENU) {

        } else if (getGameState() == GameState.PAUSED) {

        } else if (getGameState() == GameState.RUNNING) {
            if ((e.getKeyCode() == KeyEvent.VK_A)
                    || (e.getKeyCode() == KeyEvent.VK_D)
                    || (e.getKeyCode() == KeyEvent.VK_W)
                    || (e.getKeyCode() == KeyEvent.VK_S)) {
                getHero().stop();
            }
        } else if (getGameState() == GameState.STARTING) {
        } else if (getGameState() == GameState.STORE_MENU) {
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        if (getGameState() == getGameState().RUNNING) {
            shoot(e.getPoint());
        }
    }

    private void shoot(Point point) {
        if (!shotPause) {
            System.out.println("BANG");
            AudioPlayer.play("/resources/pistol_shot.wav");
            shootTime = System.currentTimeMillis();
            Velocity shootVector = TrigonometryCalculator.calculateVelocity(hero.getCenterOfMass(), point, 300);
            shootLine = new Line2D.Float(hero.getCenterOfMass().x, hero.getCenterOfMass().y, hero.getCenterOfMass().x + shootVector.x, hero.getCenterOfMass().y + shootVector.y);

            for (Zombie zombie : getZombies()) {
                if (shootLine.intersects(zombie.getObjectBoundary())) {
                    zombie.addToHealth(-10);
                    System.out.println("Zombie Hit");
                    break;
                }
            }
            shotPause = true;
            shotDelay = System.currentTimeMillis();
        }

    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        if (getGameState() == GameState.MAIN_MENU) {
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
        } else if (getGameState() == GameState.PAUSED) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(50, 50, 750, 450);

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 30));
            graphics.drawString("Paused", 390, 90);
            graphics.setColor(Color.GRAY);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 60));
            graphics.drawString("Press 2 To Continue", 200, 300);

        } else if (getGameState() == GameState.RUNNING) {
            if (currentMap != null) {
                currentMap.drawMap(graphics);
            }

            graphics.setFont(new Font("Calibri", Font.PLAIN, 30));
            graphics.setColor(Color.BLACK);
//            graphics.fillRect(100, 100, 100, 100);
            graphics.drawString("Zombie Hit # = " + this.zombieHit, 20, 20);

            if (shootLine != null) {
                graphics.setColor(Color.red);
                graphics.drawLine((int) shootLine.getX1(), (int) shootLine.getY1(), (int) shootLine.getX2(), (int) shootLine.getY2());
            }

        } else if (getGameState() == GameState.STARTING) {

        } else if (getGameState() == GameState.STORE_MENU) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(50, 50, 750, 450);
            graphics.setColor(Color.WHITE);
            graphics.fillRect(100, 100, 650, 350);

            graphics.setColor(Color.red);
            graphics.setFont(new Font("DEMON SKER", Font.PLAIN, 30));
            graphics.drawString("Store", 390, 90);
        } else if (gameState == GameState.DEAD) {

        }

    }

//<editor-fold defaultstate="collapsed" desc="MouseMotionListener">
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (getGameState() == gameState.RUNNING) {
            if (getCrosshair() != null) {
                getCrosshair().setPosition(new Point(e.getPoint().x - 15, e.getPoint().y - 15));

                getHero().setAngle((int) (TrigonometryCalculator.calculateAngle(getHero().getCenterOfMass(), getCrosshair().getCenterOfMass()) * 57));

            }
        }

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Map Item Management">
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

    @Override
    public void handleItemManagerResponse(ItemList itemList) {
        System.out.println("IM Response");
        for (Item item : itemList.getItems()) {
            System.out.println(item.getDisplay());
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf Methods">
    /**
     * @param currentLocation the current system coordinate
     * @param proposedLocation the proposed system coordinate; this point will
     * be validated against the content of the map (Items, Obstacles, and
     * Portals).
     *
     * @return the validation assessment: while the handling of the result is up
     * to the calling method, presumably, a "false" will be not allowed to go to
     * the proposed location, while a "true" will be allowed to go to this
     * location.
     */
    @Override
    public boolean validateMove(Point currentLocation, Point proposedLocation) {
        /*  Only validate if we are crossing a cell boundary, i.e. entering a 
         *  different cell than we currently occupy.
         */

        System.out.printf("Current [%d, %d] Proposed [%d, %d] \n", currentLocation.x, currentLocation.y, proposedLocation.x, proposedLocation.y);

        if (currentMap != null) {
//            System.out.println("Current Map OK");
            //check if I am crossing a cell boundary, i.e. current cell != cell(proposedLocation))
            Point cellLocationCurrent = currentMap.getCellLocation(currentLocation);
            Point cellLocationProposed = currentMap.getCellLocation(proposedLocation);

            if (!cellLocationCurrent.equals(cellLocationProposed)) {

                System.out.printf("Current Cell [%d, %d] Proposed Cell [%d, %d] \n", cellLocationCurrent.x, cellLocationCurrent.y, cellLocationProposed.x, cellLocationProposed.y);
                System.out.println("XXxxxxxxxxxxxxx");
                return currentMap.validateLocation(cellLocationProposed);
            }
        }

        return true;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ObstacleEventHandlerIntf Methods">
    @Override
    public boolean obstacleEvent(Obstacle obstacle) {
        System.out.println("Obstacle = " + obstacle.getType().toString());
        if (obstacle.getType() == ObstacleType.BARRIER) {
            
        }
        return false;
    }
//</editor-fold>

}
