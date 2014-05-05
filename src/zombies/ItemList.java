/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zombies;

import java.util.ArrayList;

/**
 *
 * @author Leo
 */
public class ItemList {
    {
        setItems(new ArrayList<Item>());
    }
    
    private ArrayList<Item>items;

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
