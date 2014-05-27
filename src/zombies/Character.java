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
public class Character extends Actor {
    
    private void initialize () {
        this.setImage(ResourceTools.loadImageFromResource("resources/character.png"));
    }

    public Character( Point position, Velocity velocity) {
        super(position, velocity);
        initialize ();
    }
}
