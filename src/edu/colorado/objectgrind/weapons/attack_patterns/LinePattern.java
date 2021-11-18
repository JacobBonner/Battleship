package edu.colorado.objectgrind.weapons.attack_patterns;

import edu.colorado.objectgrind.grid.Coordinate;
import edu.colorado.objectgrind.grid.Grid;
import edu.colorado.objectgrind.weapons.Weapon;

import java.lang.reflect.Method;

/**
 * This class defines a line pattern that a weapon can attack with.
 *
 * @see WeaponAttackPattern
 */
public class LinePattern extends WeaponAttackPattern {

    /**
     * The row offset that this line pattern uses when attacking a location.
     * <p>
     *     It represents the number of rows to go above and below the given coordinate in attackWthPattern.
     * </p>
     */
    private final int row_offset;

    /**
     * The column offset that this line pattern uses when attacking a location.
     * <p>
     *     It represents the number of columns to go right and left of the given coordinate in attackWthPattern.
     * </p>
     */
    private final int col_offset;

    public LinePattern(int row_offset, int col_offset) {
        this.row_offset = row_offset;
        this.col_offset = col_offset;
    }

    /**
     * Attacks every location in a line centered at the coordinate on the grid, by using the given attack method.
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
     * @see #row_offset
     * @see #col_offset
     */
    public void attackWithPattern(Weapon attack_weapon, Method attack_method, Grid attacked_grid, Coordinate coord_of_attack) throws Exception {

        // Get the row, column, and layer from the attacked coordinate
        int row = coord_of_attack.getRow();
        int col = coord_of_attack.getColumn();
        int layer = coord_of_attack.getLayer();

        // For every row above and below the attacked row by the row_offset
        for( int i=row-this.row_offset; i<row+this.row_offset+1; i++ ) {

            // For every column left and right of the attacked column by the col_offset
            for( int j=col-this.col_offset; j<col+this.col_offset+1; j++ ) {

                // Get the current coordinate, and call the attack method
                Coordinate curr_coord = new Coordinate(layer, i, j);
                super.callAttackMethod(attack_weapon, attack_method, attacked_grid, curr_coord);
            }
        }
    }
}
