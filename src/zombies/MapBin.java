/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zombies;

import images.ResourceTools;
import java.awt.Dimension;
import java.awt.Point;
import map.Map;
import map.ObstacleType;

/**
 *
 * @author user
 */
public class MapBin {

    public static Map getZombieMap() {
        Map zombieMap = new Map(ResourceTools.loadImageFromResource("resources/newmap.png"), new Dimension(32, 32), new Dimension(50, 50));
        Map.addObstacle(zombieMap, new Point(7, 1), ObstacleType.BARRIER);
        Map.addObstacle(zombieMap, new Point(6, 12), ObstacleType.BARRIER);
        Map.addObstacle(zombieMap, new Point(5, 22), ObstacleType.BARRIER);
        //Map.addObstacleRange(zombieMap, new Point(0, 0), new Point(2, 5), ObstacleType.BARRIER);
        //Map.addObstacleRange(zombieMap, new Point(7, 18), new Point(16, 18), ObstacleType.WATER);
        Map.addObstacleRange(zombieMap, new Point(20, 12), new Point(20, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(21, 9), new Point(21, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(22, 9), new Point(22, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(23, 9), new Point(23, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(24, 14), new Point(24, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(25, 14), new Point(25, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(26, 15), new Point(26, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(00, 0), new Point(0, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(00, 26), new Point(14, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(0, 0), new Point(74, 0), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(74, 0), new Point(74, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(0, 27), new Point(74, 27), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(70, 19), new Point(70, 21), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(73, 0), new Point(73, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(72, 20), new Point(72, 25), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(71, 19), new Point(71, 22), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(74, 0), new Point(74, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(0, 3), new Point(5, 3), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(4, 4), new Point(5, 4), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(5, 2), new Point(7, 2), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(3, 10), new Point(7, 10), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(1, 11), new Point(1, 23), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(1, 11), new Point(8, 11), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(1, 13), new Point(9, 13), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(1, 14), new Point(9, 14), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(2, 19), new Point(2, 23), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(3, 19), new Point(3, 22), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(4, 21), new Point(4, 22), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(11, 24), new Point(12, 24), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(10, 25), new Point(12, 25), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(30, 5), new Point(30, 6), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(31, 4), new Point(31, 8), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(32, 1), new Point(32, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(33, 1), new Point(33, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(34, 1), new Point(34, 11), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(34, 15), new Point(34, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(35, 1), new Point(35, 8), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(36, 1), new Point(36, 4), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(32, 1), new Point(43, 1), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(31, 7), new Point(43, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(36, 8), new Point(39, 8), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(41, 8), new Point(43, 8), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(42, 9), new Point(44, 9), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(42, 10), new Point(44, 10), ObstacleType.BARRIER);
//        Map.addObstacleRange(zombieMap, new Point(44, 26), new Point(45, 26), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(35, 18), new Point(35, 23), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(36, 20), new Point(44, 20), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(38, 19), new Point(45, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(40, 18), new Point(45, 18), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(66, 16), new Point(66, 18), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(69, 18), new Point(69, 20), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(68, 16), new Point(68, 20), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(67, 16), new Point(67, 20), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(35, 21), new Point(44, 21), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(36, 22), new Point(38, 22), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(64, 6), new Point(64, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(65, 4), new Point(65, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(68, 4), new Point(68, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(67, 4), new Point(67, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(66, 4), new Point(66, 7), ObstacleType.BARRIER);
        Map.addObstacleRange(zombieMap, new Point(69, 6), new Point(69, 7), ObstacleType.BARRIER);
        
        
        Map.addObstacleRange(zombieMap, new Point(58, 0), new Point(58, 27), ObstacleType.BARRIER);

        return zombieMap;
    }

}
