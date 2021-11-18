package edu.colorado.objectgrind.weapons.attack_patterns;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.weapons.Weapon;

import java.lang.reflect.Method;

/**
 * This class defines a single location pattern that a weapon can attack with.
 *
 * @see WeaponAttackPattern
 */
public class SingleLocationPattern extends WeaponAttackPattern {

    /**
     * Attacks the single location at the coordinate on the grid, by using the given attack method.
     *
     * @param attack_weapon the weapon that will be used to attack
     * @param attack_method the method that will be used to attack
     * @param attacked_grid the grid that will be attacked
     * @param coord_of_attack the coordinate that will be attacked
     * @throws Exception if attack_method is invalid
     * @see Weapon
     * @see Method
     * @see Grid
     * @see Coordinate
     */
    public void attackWithPattern(Weapon attack_weapon, Method attack_method, Grid attacked_grid, Coordinate coord_of_attack) throws Exception {

        // Attack the single given location with the attack method that is given
        super.callAttackMethod(attack_weapon, attack_method, attacked_grid, coord_of_attack);
    }
}
