/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zombies;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Point;

/**
 *
 * @author Leo
 */

public class Zombie extends Actor {
    //give a default picture...
    
    private void initialize(){
        this.setImage(ResourceTools.loadImageFromResource("resources/zombie.png"));
    }
    
    public Zombie(Point position, Velocity velocity) {
        super(position, velocity);
        initialize();
    }
    
}
