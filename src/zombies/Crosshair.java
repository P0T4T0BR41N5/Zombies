/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Leo
 */
public class Crosshair extends Actor {

    public Crosshair(Point position, Velocity velocity) {
        super(position, velocity);
        

       Image i = ResourceTools.loadImageFromResource("resources/crosshair.png");
        this.setImage(i.getScaledInstance(30, 30, Image.SCALE_FAST));
    }

}
