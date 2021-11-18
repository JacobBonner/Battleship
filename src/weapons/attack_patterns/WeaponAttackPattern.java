package edu.colorado.objectgrind.weapons.attack_patterns;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.weapons.Weapon;

import java.lang.reflect.Method;

/**
 * This class defines a pattern that any given weapon can attack with.
 *
 * @see Weapon
 * @see Grid
 * @see Coordinate
 */
public abstract class WeaponAttackPattern {

    /**
     * Calls the given attack method from the given weapon on the grid at the given coordinate.
     *
     * @param attack_weapon the weapon that will be used to attack
     * @param attack_method the method that will be used to attack
     * @param attacked_grid the grid that will be attacked
     * @param coord_of_attack the coordinate that will be attacked
     * @throws Exception if the given attack_method is invalid
     * @see Weapon
     * @see Method
     * @see Grid
     * @see Coordinate
     */
    public void callAttackMethod(Weapon attack_weapon, Method attack_method, Grid attacked_grid, Coordinate coord_of_attack) throws Exception {

        // If the coordinate is in bounds
        if ( attacked_grid.isCoordinateInBounds(coord_of_attack) ) {

            // Execute the given attack method
            Object[] params = new Object[] {attacked_grid, coord_of_attack};
            attack_method.invoke(attack_weapon, params);
        }
    }

    /**
     * With the given attack method, attack according to the pattern defined in this class' subclasses.
     *
     * @param attack_weapon the weapon that will be used to attack
     * @param attack_method the method that will be used to attack
     * @param attacked_grid the grid that will be attacked
     * @param coord_of_attack the coordinate that will be attacked
     * @throws Exception if the given attack_method is invalid
     */
    public abstract void attackWithPattern(Weapon attack_weapon, Method attack_method, Grid attacked_grid, Coordinate coord_of_attack) throws Exception;
}
