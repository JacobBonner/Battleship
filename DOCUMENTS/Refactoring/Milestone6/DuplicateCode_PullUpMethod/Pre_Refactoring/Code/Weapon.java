package edu.colorado.objectgrind.Weapons;

import edu.colorado.objectgrind.Grid.Grid;
import edu.colorado.objectgrind.Player;

public abstract class Weapon {

    // Private attributes
    private int num_uses;

    public Weapon(int uses) {
        this.num_uses = uses;
    }

    // Gets the number of uses
    public int getNumUses() {
        return this.num_uses;
    }

    // Decreases the number of uses for the weapon
    public void decreaseNumUses() {
        this.num_uses--;
    }

    // Implements the functionality of the weapon
    public abstract void useWeapon(Player attacked_player, Grid.Coordinate coord_of_attack);
}
