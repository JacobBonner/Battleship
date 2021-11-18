package edu.colorado.objectgrind.ships.captains_quarters;

/**
 * This defines the captain's quarters behavior for all of the surface ships - battleship, destroyer, minesweeper.
 *
 * @see CaptainsQuarters
 * @see edu.colorado.objectgrind.ships.ship_types.Battleship
 * @see edu.colorado.objectgrind.ships.ship_types.Destroyer
 * @see edu.colorado.objectgrind.ships.ship_types.Minesweeper
 */
public class SurfaceCaptainsQuarters extends CaptainsQuarters {

    /**
     * Class constructor that passes set values to the super class for each parameter in the super class' constructor.
     * <p>
     *     For a surface ship, a location is a captain's quarters vertically if the location one row down is the same
     *     ship, and if the location two rows down is not the same ship. This is why the constructor
     *     passes {1} for same_ship_rows and {2} for not_ship_rows to the super class's constructor.
     * </p>
     * <p>
     *     For a surface ship, a location is a captain's quarters horizontally if the location one column to the right
     *     is the same ship, and if the location two columns to the right is not the same ship. This is why the constructor
     *     passes {1} for same_ship_cols and {2} for not_ship_cols to the super class's constructor.
     * </p>
     */
    public SurfaceCaptainsQuarters() {
        super( new int[] {1}, new int[] {2}, new int[] {1}, new int[] {2} );
    }
}
