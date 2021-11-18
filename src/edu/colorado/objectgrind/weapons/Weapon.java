package edu.colorado.objectgrind.weapons;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.game.Player;
import edu.colorado.objectgrind.weapons.attack_patterns.WeaponAttackPattern;

import java.lang.reflect.Method;

/**
 * This class defines a weapon that a player can use during a game.
 *
 * @see WeaponAttackPattern
 */
public abstract class Weapon {

    /**
     * The number of times this weapon can be used.
     */
    private int num_uses;

    /**
     * The layers of a grid that this weapon can be used on.
     */
    protected final int[] valid_layers;

    /**
     * The pattern that this weapon attacks with.
     */
    protected final WeaponAttackPattern attack_pattern;

    /**
     * The number of ships a player must sink until they can use this weapon.
     */
    private final int sinks_until_usable;

    /**
     * Class constructor specifying values for all of this weapon's attributes.
     *
     * @param uses the number of uses this weapon will have
     * @param valid_layers the layers that will be valid for using this weapon
     * @param attack_pattern the attack pattern that this weapon will have and use
     * @param sinks_needed the number of ships a player must sink in order to use this weapon
     */
    public Weapon(int uses, int[] valid_layers, WeaponAttackPattern attack_pattern, int sinks_needed) {
        this.num_uses = uses;
        this.valid_layers = valid_layers;
        this.attack_pattern = attack_pattern;
        this.sinks_until_usable = sinks_needed;
    }

    /**
     * Gets the number of ships a player needs to sink before using this weapon.
     *
     * @return the current value of sinks_until_usable
     * @see #sinks_until_usable
     */
    public int getSinksNeeded() {
        return this.sinks_until_usable;
    }

    /**
     * Gets the number of uses that this weapon has.
     *
     * @return the current value of num_uses
     * @see #num_uses
     */
    public int getNumUses() {
        return this.num_uses;
    }

    /**
     * Decreases the number of uses this weapon has by one.
     * @see #num_uses
     */
    public void decreaseNumUses() {
        this.num_uses--;
    }

    /**
     * Implements the behavior of this weapon on a single location of the given grid.
     *
     * @param attacked_grid the grid that will be attacked by this weapon
     * @param coord the coordinate that this weapon will be used at
     */
    public abstract void attackLocation(Grid attacked_grid, Coordinate coord);

    /**
     * Prints the name of this weapon.
     */
    public abstract void printWeapon();

    /**
     * Uses this weapon over its entire attack pattern on all of its valid layers.
     *
     * @param attacked_player the player who is being attacked by this weapon
     * @param coord_of_attack the coordinate where this weapon is being used
     * @throws Exception is the attack method is invalid
     * @see #printWeapon()
     * @see #decreaseNumUses()
     * @see #valid_layers
     * @see #attack_pattern
     * @see Method
     * @see WeaponAttackPattern#attackWithPattern(Weapon, Method, Grid, Coordinate)
     */
    public void useWeapon(Player attacked_player, Coordinate coord_of_attack) throws Exception {

        // Print the weapon that is being used
        System.out.print("Using ");
        printWeapon();

        // Save the row and column from the coordinate
        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();

        // For each layer that this weapon can attack
        for ( int layer : this.valid_layers ) {

            // Use attackLocation according to the attack_pattern
            Coordinate new_coord = new Coordinate(layer, row, col);
            Method func_to_pass = Weapon.class.getMethod("attackLocation", Grid.class, Coordinate.class);
            this.attack_pattern.attackWithPattern(this, func_to_pass, attacked_player.getGrid(), new_coord);
        }

        // Decrease the number of uses of this weapon
        decreaseNumUses();
    }
}
