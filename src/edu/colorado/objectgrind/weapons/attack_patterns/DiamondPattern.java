package edu.colorado.objectgrind.weapons.attack_patterns;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.weapons.Weapon;

import java.lang.reflect.Method;

/**
 * This class defines a diamond pattern that a weapon can attack with.
 *
 * @see WeaponAttackPattern
 */
public class DiamondPattern extends WeaponAttackPattern {

    /**
     * Attacks every location in a diamond pattern centered at the coordinate on the grid, by using the given attack method.
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

        // Save the row and column of the attacked coordinate
        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();
        int layer = coord_of_attack.getLayer();

        // Centered on the given coordinate, iterate over the sonar's range and update locations accordingly
        for (int i=row-2; i<row+2+1; i++) { // rows

            // Determine the offset on both sides of the column for the given row
            int offset = -1;
            if ( (i == row-2) | (i == row+2) ) offset = 0;
            else if ( (i == row-1) | (i == row+1) ) offset = 1;
            else if ( i == row ) offset = 2;

            for (int j=col-offset; j<col+offset+1; j++) { // columns

                Coordinate curr_coord = new Coordinate(layer, i, j);
                super.callAttackMethod(attack_weapon, attack_method, attacked_grid, curr_coord);
            }
        }
    }
}
