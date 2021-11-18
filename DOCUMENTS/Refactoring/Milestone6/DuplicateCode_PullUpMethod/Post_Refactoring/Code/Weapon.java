package edu.colorado.objectgrind.weapons;

import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

import java.lang.reflect.Method;

public abstract class Weapon {

    // Private attributes
    private int num_uses;
    protected int[] valid_layers;
    protected WeaponAttackPattern attack_pattern;
    private final int sinks_until_usable;

    public Weapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        this.num_uses = uses;
        this.valid_layers = valid_layers;
        this.attack_pattern = attack_pattern;
        this.sinks_until_usable = sinks_needed;
    }

    // Gets the number of sinks needed until teh weapon is usable
    public int getSinksNeeded() {
        return this.sinks_until_usable;
    }

    // Gets the number of uses
    public int getNumUses() {
        return this.num_uses;
    }

    // Decreases the number of uses for the weapon
    public void decreaseNumUses() {
        this.num_uses--;
    }

    // Implements the behavior of the weapon on a single location
    public abstract void attackLocation(Grid attacked_grid, Grid.Coordinate coord);

    // Abstract print weapon method
    public abstract void printWeapon();

    // Implements the functionality of the weapon over the entire pattern
    public void useWeapon(Player attacked_player, Grid.Coordinate coord_of_attack) throws Exception {

        // Print the weapon that is being used
        System.out.print("Using ");
        printWeapon();

        // Save the row and column from the coordinate
        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();

        for ( int layer : this.valid_layers ) {

            // Use attackLocation according to the attack_pattern
            Grid.Coordinate new_coord = new Grid.Coordinate(layer, row, col);
            Method func_to_pass = Weapon.class.getMethod("attackLocation", Grid.class, Grid.Coordinate.class);
            this.attack_pattern.attackWithPattern(this, func_to_pass, attacked_player.getGrid(), new_coord);
        }

        decreaseNumUses();
    }
}
